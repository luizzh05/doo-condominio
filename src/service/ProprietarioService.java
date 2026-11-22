package service;

import model.Proprietario;
import model.DAO.ProprietarioDAO;

public class ProprietarioService extends CrudService<Proprietario> {
    public ProprietarioService() {
        super(new ProprietarioDAO());
    }

    @Override
    public void validar(Proprietario registro) {
        super.validar(registro);
        PessoaValidation.validar(registro);
        if ("Física".equals(registro.getTipoPessoa())) {
            if (registro.getCpf() == null) throw new IllegalArgumentException("Informe o CPF da pessoa física.");
        } else if (registro.getCnpj() == null) {
            throw new IllegalArgumentException("Informe o CNPJ da pessoa jurídica.");
        }
    }
}
