package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.exception.DAOException;
import br.edu.ifsc.fln.model.domain.Cor;
import br.edu.ifsc.fln.model.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CorDAO {

    // 🔹 INSERT
    public void inserir(Cor cor) throws DAOException {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(cor);
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
    public void alterar(Cor cor) throws DAOException{
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(cor);
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
    public void remover(Cor cor) throws DAOException{
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Cor c = session.contains(cor) ? cor : session.merge(cor);
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

            Cor cor = session.find(Cor.class, id);
            if (cor != null) {
                session.remove(cor);
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
    public List<Cor> listar() throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session
                    .createQuery("FROM Cor", Cor.class)
                    .list();
        } catch (Exception e) {
            throw new DAOException("Não foi possível realizar a pesquisa no banco de dados", e);
        }
    }

//    // 🔹 LISTAR POR NOME (LIKE)
//    public List<Cor> listarPorNome(String nome) throws DAOException{
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session
//                    .createQuery("FROM Cor WHERE lower(nome) LIKE :nome ORDER BY nome", Cor.class)
//                    .setParameter("nome", "%" + nome.toLowerCase() + "%")
//                    .list();
//        } catch (Exception ex) {
//            throw new DAOException("Não foi possível realizar a pesquisa no banco de dados", ex);
//        }
//    }

//    // 🔹 PAGINAÇÃO
//    public List<Cor> listarPaginado(int pagina, int tamanhoPagina) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session
//                    .createQuery("FROM Cor ORDER BY nome", Cor.class)
//                    .setFirstResult((pagina - 1) * tamanhoPagina)
//                    .setMaxResults(tamanhoPagina)
//                    .list();
//        }
//    }

    // 🔹 BUSCAR POR ID
    public Cor buscarPorId(int id) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Cor.class, id);
        } catch (Exception ex) {
            throw new DAOException("Não foi possível realizar a pesquisa no banco de dados", ex);
        }
    }
}