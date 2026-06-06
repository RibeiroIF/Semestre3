package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.Veiculo;
import br.edu.ifsc.fln.model.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VeiculoDAO {

    public boolean inserir(Veiculo veiculo) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.persist(veiculo);

            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    public boolean alterar(Veiculo veiculo) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.merge(veiculo);

            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    public boolean remover(int id) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Veiculo f = session.find(Veiculo.class, id);

            if (f != null) {
                session.remove(f);
            }

            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    public boolean remover(Veiculo veiculo) {
        return remover(veiculo.getId());
    }

    public Veiculo buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Veiculo.class, id);
        }
    }

    public List<Veiculo> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Veiculo", Veiculo.class).list();
        }
    }

    public List<Veiculo> listarPorNome(String nome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Veiculo WHERE lower(placa) LIKE :placa",
                            Veiculo.class
                    ).setParameter("placa", "%" + nome.toLowerCase() + "%")
                    .list();
        }
    }
}