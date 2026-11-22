package service;

import java.util.List;
import java.util.Objects;
import model.DAO.InterfaceDAO;

/** Operacoes de aplicacao compartilhadas pelos cadastros e consultas. */
public class CrudService<T> {
    private final InterfaceDAO<T> dao;

    public CrudService(InterfaceDAO<T> dao) {
        this.dao = Objects.requireNonNull(dao);
    }

    public List<T> consultar(String campo, String filtro) {
        return dao.retrieve(campo, filtro);
    }

    public T consultar(int id) {
        return dao.retrieve(id);
    }

    public void validar(T registro) {
        Objects.requireNonNull(registro, "Registro obrigatório");
    }

    public void salvar(T registro, int id) {
        validar(registro);
        if (id == 0) dao.create(registro);
        else dao.update(registro);
    }

    public void excluir(T registro) {
        Objects.requireNonNull(registro, "Registro obrigatório");
        dao.delete(registro);
    }
}
