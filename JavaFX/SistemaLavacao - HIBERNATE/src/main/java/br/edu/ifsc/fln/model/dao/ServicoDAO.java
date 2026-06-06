package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.exception.DAOException;
import br.edu.ifsc.fln.model.domain.Servico;
import br.edu.ifsc.fln.model.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ServicoDAO {

    // 🔹 INSERT
    public void inserir(Servico servico) throws DAOException {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(servico);
            tx.commit();
            //return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new DAOException("Não foi possível salvar o registro no banco de dados!", e);
            //return false;
        }
    }

    // 🔹 UPDATE
    public void alterar(Servico servico) throws DAOException{
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(servico);
            tx.commit();
            //return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new DAOException("Não foi possível atualizar o registro no banco de dados.", e);
            //return false;
        }
    }

    // 🔹 DELETE por objeto
    public void remover(Servico servico) throws DAOException{
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Servico c = session.contains(servico) ? servico : session.merge(servico);
            session.remove(c);

            tx.commit();
            //return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new DAOException("Não foi possível excluir  o registro do banco de dados.", e);
            //return false;
        }
    }

    // 🔹 DELETE por ID
    public void excluir(int id) throws DAOException {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Servico servico = session.find(Servico.class, id);
            if (servico != null) {
                session.remove(servico);
            }

            tx.commit();
            //return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new DAOException("Não foi possível excluir  o registro do banco de dados.", e);
            //return false;
        }
    }

    // 🔹 LISTAR TODOS
    public List<Servico> listar() throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session
                    .createQuery("FROM Servico ORDER BY descricao", Servico.class)
                    .list();
        } catch (Exception e) {
            throw new DAOException("Não foi possível realizar a pesquisa no banco de dados", e);
        }
    }

    // 🔹 LISTAR POR NOME (LIKE)
    public List<Servico> listarPorNome(String nome) throws DAOException{
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session
                    .createQuery("FROM Servico WHERE lower(descricao) LIKE :nome ORDER BY descricao", Servico.class)
                    .setParameter("nome", "%" + nome.toLowerCase() + "%")
                    .list();
        } catch (Exception ex) {
            throw new DAOException("Não foi possível realizar a pesquisa no banco de dados", ex);
        }
    }

    // 🔹 PAGINAÇÃO
    public List<Servico> listarPaginado(int pagina, int tamanhoPagina) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session
                    .createQuery("FROM Servico ORDER BY descricao", Servico.class)
                    .setFirstResult((pagina - 1) * tamanhoPagina)
                    .setMaxResults(tamanhoPagina)
                    .list();
        }
    }

    // 🔹 BUSCAR POR ID
    public Servico buscarPorId(int id) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Servico.class, id);
        } catch (Exception ex) {
            throw new DAOException("Não foi possível realizar a pesquisa no banco de dados", ex);
        }
    }
}