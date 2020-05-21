package br.com.contmatic.model.restricoes;

/**
 * The Class ConstantesString.
 */
public class RestricaoCampo {
        
    /** The Constant TAMANHO_REGULAR. */
    public static final int TAMANHO_REGULAR = 100;
    
    /** The Constant CEP. */
    public static final int TAMANHO_CEP = 8;
    
    /** The Constant MAX_NUMERO_ENDERECO. */
    public static final int MAX_NUMERO_ENDERECO = 6;
    
    /** The Constant NUMERO_CELULAR. */
    public static final int NUMERO_CELULAR = 9;
    
    /** The Constant NUMERO_TELEFONE. */
    public static final int NUMERO_TELEFONE = 8;
    
    /** The Constant CPF. */
    public static final int CPF = 11;
    
    /** The Constant MAX_NUMERO_CONTA. */
    public static final int MAX_NUMERO_CONTA = 12;
    
    /** The Constant MAX_NUMERO_AGENCIA. */
    public static final int MAX_NUMERO_AGENCIA = 5;
    
    /** The Constant MAX_CODIGO_BANCO. */
    public static final int MAX_CODIGO_BANCO = 4;
    
    /** The Constant CNPJ. */
    public static final int CNPJ = 14;
	
	/** The Constant ESPACO. */
	public static final String ESPACO = " ";
	
    /** The Constant NUMERAL. */
    public static final String NUMERAL = "[0-9]";
    
    /** The Constant APENAS_LETRA_NUMERAL. */
    public static final String APENAS_LETRA_NUMERAL = "[A-Za-z0-9]";
    
    /** The Constant EMAIL. */
    public static final String EMAIL = "^[a-z0-9][a-z0-9._-]{0,29}(?<![._-])@[a-z0-9][a-z0-9.-]{0,19}(?<![.-])\\.[a-z]{2,6}$";    
    
    /** The Constant NOME. */
    public static final String NOME = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ &\\-ªº\\.']{1," + (TAMANHO_REGULAR) + "}";
    
    /** The Constant CELULAR. */
    public static final String CELULAR = NUMERAL + "{" + NUMERO_CELULAR + "}";
    
    /** The Constant TELEFONE. */
    public static final String TELEFONE = NUMERAL + "{" + NUMERO_TELEFONE + "}";
    
    /** The Constant CEP. */
    public static final String CEP = NUMERAL + "{" + TAMANHO_CEP + "}";
    
    /** The Constant NUMERO_ENDERECO. */
    public static final String NUMERO_ENDERECO = NUMERAL + "{1," + MAX_NUMERO_ENDERECO + "}";
    
    /** The Constant CODIGO_BANCO. */
    public static final String CODIGO_BANCO = APENAS_LETRA_NUMERAL + "{1," + MAX_CODIGO_BANCO + "}";
    
    /** The Constant NUMERO_AGENCIA. */
    public static final String NUMERO_AGENCIA = APENAS_LETRA_NUMERAL + "{1," + MAX_NUMERO_AGENCIA + "}";
    
    /** The Constant NUMERO_CONTA. */
    public static final String NUMERO_CONTA = APENAS_LETRA_NUMERAL + "{1," + MAX_NUMERO_CONTA + "}";
    
    /** The Constant RAZAO_SOCIAL. */
    public static final String COMPLEMENTO = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-\\.)('ªº°,:\\/\\\\ ]{1," + (TAMANHO_REGULAR) + "}";
    
    /**
     * Instantiates a new constantes string.
     */
    private RestricaoCampo() {
    }

}
