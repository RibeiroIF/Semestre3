package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.exception.DAOException;
import br.edu.ifsc.fln.exception.ExceptionLavacao;
import br.edu.ifsc.fln.exception.OrdemServicoException;
import br.edu.ifsc.fln.model.domain.*;
import br.edu.ifsc.fln.model.dto.OrdensMensaisDTO;
import br.edu.ifsc.fln.model.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdemServicoDAO {

    // INSERIR ORDEM DE SERVIÇO (PRINCIPAL)
    public void inserir(OrdemServico ordemServico) throws ExceptionLavacao, Exception{
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Veiculo veiculo = session.find(Veiculo.class, ordemServico.getVeiculo().getId());

            ordemServico.setVeiculo(veiculo);

            double totalCalculado = 0.0;

            for (ItemOS item : ordemServico.getItens()) {
                Servico servico = session.find(Servico.class, item.getServico().getId());
                item.setServico(servico);
                //item.getValorServico();
                item.setOrdemServico(ordemServico);
                totalCalculado += item.getValorServico();
            }
            ordemServico.getTotal();

            // cascade salva itens automaticamente
            session.persist(ordemServico);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new Exception("Falha na operação de ordemServico", e);
        }
    }

    public void alterar(OrdemServico ordemServico) throws ExceptionLavacao, Exception {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            //  ordemServico persistida
            OrdemServico ordemServicoDB = session.find(OrdemServico.class, ordemServico.getId());

            if (ordemServicoDB == null) {
                throw new RuntimeException("OrdemServico não encontrada");
            }

            if (ordemServicoDB.getStatus() == EStatus.FECHADA || ordemServicoDB.getStatus() == EStatus.CANCELADA){
                throw new RuntimeException("Ordem de serviço não aceita alterações");
            }

            //  2. LIMPAR ITENS ANTIGOS
            ordemServicoDB.getItens().clear();

            double novoTotal = 0.0;

            //  3. INSERIR NOVOS ITENS
            for (ItemOS item : ordemServico.getItens()) {
                Servico servico = session.find(Servico.class, item.getServico().getId());

                // recria item
                ItemOS novoItem = new ItemOS();
                novoItem.setServico(servico);
                novoItem.setValorServico(item.getValorServico());
                novoItem.setOrdemServico(ordemServicoDB);
                ordemServicoDB.getItens().add(novoItem);
                novoTotal += novoItem.getValorServico();
            }

            // atualizar dados da ordemServico
            ordemServico.calcularServico();
            ordemServicoDB.setStatus(ordemServico.getStatus());
            tx.commit();
        } catch (ExceptionLavacao ex) {
            throw new ExceptionLavacao(ex.getMessage());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new Exception("Falha na atualização do registro de ordemServico");
        }
    }

    public void cancelar(int idOrdemServico) throws OrdemServicoException {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            OrdemServico ordemServico = session.find(OrdemServico.class, idOrdemServico);

            if (ordemServico == null) {
                throw new OrdemServicoException("OrdemServico não encontrada");
            }
            if (ordemServico.getStatus() == EStatus.CANCELADA) {
                throw new OrdemServicoException("OrdemServico já cancelada."); // já cancelada
            }
            ordemServico.setStatus(EStatus.CANCELADA);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new OrdemServicoException("Falha no cancelamento da ordemServico.");
        }
    }

    // LISTAR TODAS
    public List<OrdemServico> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "SELECT DISTINCT v FROM OrdemServico v " +
                            "LEFT JOIN FETCH v.itens " +
                            "ORDER BY v.agenda DESC",
                    OrdemServico.class
            ).getResultList();
        }
    }

    // BUSCAR POR ID
    public OrdemServico buscar(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(OrdemServico.class, id);
        }
    }

    // REMOVER
    public void remover(int id) throws ExceptionLavacao, Exception{
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            OrdemServico ordemServico = session.find(OrdemServico.class, id);

            if (ordemServico != null) {
                session.remove(ordemServico);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new Exception("Falha na remoção da OrdemServico");
        }
    }

    public Map<Integer, ArrayList<Integer>> listarQuantidadeOrdensMensais() {
        Map<Integer, ArrayList<Integer>> retorno = new HashMap<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<Object[]> resultados = session.createQuery(
                    "SELECT COUNT(v.id), YEAR(v.agenda), MONTH(v.agenda) " +
                            "FROM OrdemServico v " +
                            "GROUP BY YEAR(v.agenda), MONTH(v.agenda) " +
                            "ORDER BY YEAR(v.agenda), MONTH(v.agenda)",
                    Object[].class
            ).getResultList();

            for (Object[] row : resultados) {

                Long count = (Long) row[0];
                Integer ano = (Integer) row[1];
                Integer mes = (Integer) row[2];

                if (!retorno.containsKey(ano)) {
                    ArrayList<Integer> linha = new ArrayList<>();
                    linha.add(mes);
                    linha.add(count.intValue());
                    retorno.put(ano, linha);
                } else {
                    ArrayList<Integer> linha = retorno.get(ano);
                    linha.add(mes);
                    linha.add(count.intValue());
                }
            }

            return retorno;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    /**
     * Tornando o método mais elegante com uso de DTO
     * DAO com HQL
     */
    public List<OrdensMensaisDTO> listarQuantidadeOrdensAnuais() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "SELECT new br.edu.ifsc.fln.model.dto.OrdensMensaisDTO(" +
                            "YEAR(v.agenda), MONTH(v.agenda), COUNT(v.id)) " +
                            "FROM OrdemServico v " +
                            "GROUP BY YEAR(v.agenda), MONTH(v.agenda) " +
                            "ORDER BY YEAR(v.agenda), MONTH(v.agenda)",
                    OrdensMensaisDTO.class
            ).getResultList();

        }
    }
}