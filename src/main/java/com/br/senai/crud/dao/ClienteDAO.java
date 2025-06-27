package com.br.senai.crud.dao;
import com.br.senai.crud.Cliente;
import com.br.senai.crud.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClienteDAO {

    public void salvar(Cliente cliente) {
        Transaction transaction = null; // Cria uma transação
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Abre uma sessão com o banco. O try-with-resources fecha automaticamente depois
            transaction = session.beginTransaction(); // Inicia a transação

            session.save(cliente); // Salva o objeto no banco (INSERT)

            transaction.commit(); // Confirma as alterações no banco
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback(); // Desfaz as alterações se algo der errado
            }
            ex.printStackTrace(); // Mostra o erro no console
        }
    }

    public void atualizar(Cliente cliente) {
        Transaction transaction = null; // Cria uma transação
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Inicia a transação

            session.update(cliente); // Atualiza o cliente no banco (UPDATE)

            transaction.commit(); // Confirma as alterações
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback(); // Cancela se houver erro
            }
            ex.printStackTrace(); // Mostra o erro
        }
    }

    public void deletar(int id) {
        Transaction transaction = null; // Cria uma transação
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Inicia a transação
            Cliente cliente = session.get(Cliente.class, id); // Busca o cliente pelo ID
            if (cliente != null) {
                session.delete(cliente); // Deleta o cliente se ele existir
            }
            transaction.commit(); // Confirma a exclusão
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback(); // Cancela se houver erro
            }
            ex.printStackTrace(); // Mostra o erro
        }
    }

    public Cliente buscarPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Cliente.class, id); // Retorna o cliente com o ID informado
        } catch (Exception ex) {
            ex.printStackTrace(); // Mostra o erro
            return null; // Retorna null se der erro
        }
    }

    public List<Cliente> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Cria uma query para buscar todos os clientes
            Query<Cliente> query = session.createQuery("from Cliente", Cliente.class);
            return query.list(); // Retorna a lista de clientes
        } catch (Exception ex) {
            ex.printStackTrace(); // Mostra o erro
            return null; // Retorna null se der erro
        }
    }
}