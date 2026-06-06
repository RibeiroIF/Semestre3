package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.exception.DAOException;
import br.edu.ifsc.fln.model.domain.Marca;
import br.edu.ifsc.fln.model.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MarcaDAO {

    // 🔹 INSERT
    public void inserir(Marca marca) throws DAOException {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(marca);
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
    public void alterar(Marca marca) throws DAOException{
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(marca);
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
    public void remover(Marca marca) throws DAOException{
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Marca c = session.contains(marca) ? marca : session.merge(marca);
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

            Marca marca = session.find(Marca.class, id);
            if (marca != null) {
                session.remove(marca);
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
    public List<Marca> listar() throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session
                    .createQuery("FROM Marca ORDER BY nome", Marca.class)
                    .list();
        } catch (Exception e) {
            throw new DAOException("Não foi possível realizar a pesquisa no banco de dados", e);
        }
    }

    // 🔹 LISTAR POR NOME (LIKE)
    public List<Marca> listarPorNome(String nome) throws DAOException{
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session
                    .createQuery("FROM Marca WHERE lower(nome) LIKE :nome ORDER BY nome", Marca.class)
                    .setParameter("nome", "%" + nome.toLowerCase() + "%")
                    .list();
        } catch (Exception ex) {
            throw new DAOException("Não foi possível realizar a pesquisa no banco de dados", ex);
        }
    }

    // 🔹 PAGINAÇÃO
    public List<Marca> listarPaginado(int pagina, int tamanhoPagina) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session
                    .createQuery("FROM Marca ORDER BY nome", Marca.class)
                    .setFirstResult((pagina - 1) * tamanhoPagina)
                    .setMaxResults(tamanhoPagina)
                    .list();
        }
    }

    // 🔹 BUSCAR POR ID
    public Marca buscarPorId(int id) throws DAOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Marca.class, id);
        } catch (Exception ex) {
            throw new DAOException("Não foi possível realizar a pesquisa no banco de dados", ex);
        }
    }
}