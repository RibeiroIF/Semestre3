package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.ECategoria;
import br.edu.ifsc.fln.model.domain.Modelo;
import br.edu.ifsc.fln.model.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ModeloDAO {

    // 🔹 INSERIR
    public boolean inserir(Modelo modelo) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.persist(modelo);

            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 ATUALIZAR
    public boolean alterar(Modelo modelo) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.merge(modelo);

            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 REMOVER
    public boolean remover(int id) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Modelo modelo = session.find(Modelo.class, id);

            if (modelo != null) {
                session.remove(modelo); // cascade remove estoque
            }

            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean remover(Modelo modelo) {
        return remover(modelo.getId());
    }

    // 🔹 LISTAR TODOS
    public List<Modelo> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Modelo ORDER BY descricao",
                    Modelo.class
            ).list();
        }
    }

//    public List<Modelo> listar(ECategoria categoria) {
//
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//
//            return session.createQuery(
//                            "FROM Modelo WHERE lower(categoria) LIKE :nome ORDER BY categoria",
//                            Modelo.class
//                    )
//                    .setParameter("categoria", categoria)
//                    .getResultList();
//        }
//    }

    // 🔹 BUSCAR POR ID
    public Modelo buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Modelo.class, id);
        }
    }

    // 🔹 LISTAR POR NOME
    public List<Modelo> listarPorNome(String nome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Modelo WHERE lower(descricao) LIKE :nome ORDER BY descricao",
                            Modelo.class
                    ).setParameter("nome", "%" + nome.toLowerCase() + "%")
                    .list();
        }
    }

//    // 🔹 LISTAR POR CATEGORIA
//    public List<Modelo> listarPorCategoria(int idCategoria) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery(
//                            "FROM Modelo WHERE categoria.id = :idCategoria ORDER BY descricao",
//                            Modelo.class
//                    ).setParameter("idCategoria", idCategoria)
//                    .list();
//        }
//    }

    // 🔹 PESQUISA (nome + descrição)
    public List<Modelo> pesquisar(String termo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Modelo " +
                                    "WHERE lower(descricao) LIKE :termo " +
                                    "ORDER BY descricao",
                            Modelo.class
                    ).setParameter("termo", "%" + termo.toLowerCase() + "%")
                    .list();
        }
    }

//    public List<Modelo> listarPorFornecedor(int idFornecedor) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery(
//                            "FROM Modelo WHERE fornecedor.id = :idFornecedor ORDER BY nome",
//                            Modelo.class
//                    ).setParameter("idFornecedor", idFornecedor)
//                    .list();
//        }
//    }
}