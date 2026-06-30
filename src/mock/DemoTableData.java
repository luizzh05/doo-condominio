package mock;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public final class DemoTableData {

    private DemoTableData() {
    }

    public static void carregarAreasCompartilhadas(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Salao de Festas", "Ativo"},
            {2, "Piscina", "Ativo"},
            {3, "Academia", "Inativo"}
        });
    }

    public static void carregarAreasCompartilhadasEdificio(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Salao de Festas", "Residencial Aurora", "Ativo"},
            {2, "Piscina", "Residencial Aurora", "Ativo"},
            {3, "Academia", "Condominio Bela Vista", "Ativo"}
        });
    }

    public static void carregarCondominios(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Apartamento 101", 6, 2026, "850,00", "850,00", "Pago"},
            {2, "Apartamento 102", 6, 2026, "850,00", "0,00", "Aberto"},
            {3, "Sala Comercial 01", 6, 2026, "1.250,00", "1.250,00", "Pago"}
        });
    }

    public static void carregarCustosNivel1(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Manutencao", "Despesa", "Ativo"},
            {2, "Servicos Terceirizados", "Despesa", "Ativo"},
            {3, "Receitas Condominiais", "Receita", "Ativo"}
        });
    }

    public static void carregarCustosNivel2(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Elevador", "Manutencao", "Ativo"},
            {2, "Limpeza", "Servicos Terceirizados", "Ativo"},
            {3, "Taxa Condominial", "Receitas Condominiais", "Ativo"}
        });
    }

    public static void carregarEdificios(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Residencial Aurora", "12.345.678/0001-90", "Florianopolis", "Ativo"},
            {2, "Condominio Bela Vista", "98.765.432/0001-10", "Sao Jose", "Ativo"},
            {3, "Edificio Porto Azul", "45.678.901/0001-22", "Palhoca", "Inativo"}
        });
    }

    public static void carregarFornecedores(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Limpa Sul", "Limpa Sul Servicos Ltda", "11.222.333/0001-44", "Ativo"},
            {2, "Tec Elevadores", "Tec Elevadores Ltda", "22.333.444/0001-55", "Ativo"},
            {3, "Portaria Segura", "Portaria Segura ME", "33.444.555/0001-66", "Ativo"}
        });
    }

    public static void carregarFuncoesMandato(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Sindico Profissional", "Carlos Mendes", "Residencial Aurora", "01/01/2026", "31/12/2026", "Ativo"},
            {2, "Administradora", "Ana Ribeiro", "Condominio Bela Vista", "01/02/2026", "31/01/2027", "Ativo"}
        });
    }

    public static void carregarLeituras(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Apartamento 101", "Agua", 6, 2026, "148,5", "Lancada"},
            {2, "Apartamento 102", "Gas", 6, 2026, "72,0", "Lancada"},
            {3, "Sala Comercial 01", "Agua", 6, 2026, "96,3", "Pendente"}
        });
    }

    public static void carregarMovimentosCaixa(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Despesa", "Residencial Aurora", "Limpa Sul", "2.400,00", "2.400,00", "Pago"},
            {2, "Despesa", "Residencial Aurora", "Tec Elevadores", "1.850,00", "0,00", "Aberto"},
            {3, "Receita", "Condominio Bela Vista", "Moradores", "8.500,00", "7.650,00", "Parcial"}
        });
    }

    public static void carregarPessoas(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Mariana Costa", "Mariana Costa", "123.456.789-10", "", "Ativo"},
            {2, "Limpa Sul", "Limpa Sul Servicos Ltda", "", "11.222.333/0001-44", "Ativo"},
            {3, "Carlos Mendes", "Carlos Mendes", "987.654.321-00", "", "Ativo"}
        });
    }

    public static void carregarProprietarios(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Mariana Costa", "123.456.789-10", "", "Ativo"},
            {2, "Joao Pereira", "234.567.890-11", "", "Ativo"},
            {3, "Invest Alpha Ltda", "", "44.555.666/0001-77", "Ativo"}
        });
    }

    public static void carregarReservas(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Salao de Festas", "05/07/2026", "05/07/2026", "Confirmada"},
            {2, "Piscina", "12/07/2026", "12/07/2026", "Pendente"},
            {3, "Academia", "15/07/2026", "15/07/2026", "Cancelada"}
        });
    }

    public static void carregarSindicosProfissionais(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Carlos Mendes", "987.654.321-00", "CRA-SC 12345", "Ativo"},
            {2, "Ana Ribeiro", "876.543.210-99", "CRA-SC 67890", "Ativo"}
        });
    }

    public static void carregarUnidades(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Apartamento 101", "Residencial", "85,0", "Residencial Aurora", "Ativo"},
            {2, "Apartamento 102", "Residencial", "85,0", "Residencial Aurora", "Ativo"},
            {3, "Sala Comercial 01", "Comercial", "62,5", "Condominio Bela Vista", "Ativo"}
        });
    }

    public static void carregarUnidadesCondominos(JTable tabela) {
        carregar(tabela, new Object[][]{
            {1, "Apartamento 101", "Mariana Costa", "10/01/2024", "", "Ativo"},
            {2, "Apartamento 102", "Joao Pereira", "15/03/2025", "", "Ativo"},
            {3, "Sala Comercial 01", "Invest Alpha Ltda", "20/08/2023", "", "Ativo"}
        });
    }

    private static void carregar(JTable tabela, Object[][] linhas) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);

        for (Object[] linha : linhas) {
            modelo.addRow(linha);
        }
    }
}
