package models;

import gals.Constants;
import gals.LexicalError;
import gals.Lexico;
import gals.Token;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vinicius Ferneda de Lima
 */
public class Compilador {

    private Lexico lexico;
    
    public Compilador(){
        this.lexico = new Lexico();
    }
    
    /**
     * Realiza a validação lexica do código
     * @param codigoCompilar
     * @return 
     * @throws LexicalErrorAdapter
     * @throws Exception 
     */
    public String compilarLexico(String codigoCompilar) throws LexicalErrorAdapter, Exception {
        int linha = 0;
        ClasseID classe;
        String ultimaLinhaDoCodigoAnalisada = "";
        try {
            List<TokenAdapter> lTokenAdapter = new ArrayList<TokenAdapter>();

            String quebraLinha = "\n";
            String[] linhasDoCodigo = codigoCompilar.split(quebraLinha);
            for (String linhaDoCodigo : linhasDoCodigo) {
                ultimaLinhaDoCodigoAnalisada = linhaDoCodigo;
                linhaDoCodigo += quebraLinha; //devolve a quebra de linha que foi removida no split                
                lexico.setInput(new java.io.StringReader(linhaDoCodigo));

                linha++;

                Token token = lexico.nextToken();
                while (token != null) {
                    switch (token.getId()) {
                        case Constants.t_and:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_array:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_character:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_do:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_endDo:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_exit:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_false:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_if:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_ifFalseDo:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_ifTrueDo:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_logical:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_not:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_number:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_or:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_read:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_round:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_true:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_trunk:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_write:
                            classe = ClasseID.palavraReservada;
                            break;
                        case Constants.t_identificador:
                            classe = ClasseID.identificador;
                            break;
                        case Constants.t_constNum:
                            classe = ClasseID.constanteNumerica;
                            break;
                        case Constants.t_constLit:
                            classe = ClasseID.constanteLiteral;
                            break;
                        case Constants.t_TOKEN_25:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_26:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_27:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_28:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_29:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_30:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_31:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_32:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_33:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_34:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_35:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_36:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_37:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_38:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_39:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_40:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_41:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_42:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_43:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_44:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_45:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_46:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_TOKEN_47:
                            classe = ClasseID.simboloEspecial;
                            break;
                        case Constants.t_palavraReservada:
                            throw new LexicalError("Erro identificando palavraReservada", token.getPosition());
                        default:
                            throw new Exception("Tipo de token não esperado!");
                    }
                    if (classe != null) {                        
                        lTokenAdapter.add(new TokenAdapter(token, linha, classe));
                    }
                    token = lexico.nextToken();
                }
            }
            lexico.setInput(new java.io.StringReader(""));
            if (lTokenAdapter.size() > 0) {
                return montaLog(lTokenAdapter);
            }
        } catch (LexicalError ex) {
            String msg;

            if (ex.getMessage().toUpperCase().contains("CARACTERE")) {
                msg = "Erro na linha " + linha + " - " + ultimaLinhaDoCodigoAnalisada.charAt(ex.getPosition()) + " símbolo inválido";
            } else if (ex.getMessage().toUpperCase().contains("PALAVRARESERVADA")) {
                msg = "Erro na linha " + linha + " - " + recuperaPalavra(ultimaLinhaDoCodigoAnalisada, ex.getPosition()) + " palavra reservada inválida";
            } else if (ex.getMessage().toUpperCase().contains("CONSTLIT")) {
                msg = "Erro na linha " + linha + " - constante literal inválida ou não finalizada";
            } else if (ex.getMessage().toUpperCase().contains("CONSTNUM")) {
                msg = "Erro na linha " + linha + " - constante numérica inválida";
            } else if (ex.getMessage().toUpperCase().contains("IDENTIFICADOR")) {
                msg = "Erro na linha " + linha + " - " + recuperaPalavra(ultimaLinhaDoCodigoAnalisada, ex.getPosition()) + " identificador inválido";
            } else {
                msg = "Erro na linha " + linha + " – comentário de bloco não finalizado";
            }

            throw new LexicalErrorAdapter(msg, ex, linha);
        }
        
        return null;
    }
    
    private String recuperaPalavra(String linha, int posicaoInicial){
        String palavra = "";
        
        for (int i = posicaoInicial; i < linha.length(); i++) {
            if(!" ".equals(String.valueOf(linha.charAt(i)))){
                palavra += linha.charAt(i);
            }else{
                break;
            }            
        }
        
        return palavra;
    }
    
    private String montaLog(List<TokenAdapter> lTokenAdapter){
        StringBuilder codigoCompilado = new StringBuilder();
        codigoCompilado.append("linha");
        codigoCompilado.append("     ");
        codigoCompilado.append("classe");
        codigoCompilado.append("                   ");
        codigoCompilado.append("lexema");
        codigoCompilado.append("\n");
        
        for (TokenAdapter umToken : lTokenAdapter) {
            codigoCompilado.append(umToken.getLinha());            
            codigoCompilado.append(preencheEspaco(String.valueOf(umToken.getLinha()).length(), 10));
            codigoCompilado.append(umToken.getClasse().getDescricao());
            codigoCompilado.append(preencheEspaco(umToken.getClasse().getDescricao().length(), 25));
            codigoCompilado.append(umToken.getLexeme());
            codigoCompilado.append("\n");
        }
        
        codigoCompilado.append("\nPrograma compilado com sucesso!");
        
        return codigoCompilado.toString();
    }
    
    private String preencheEspaco(int stringSize, int qtdDesejada){
        String vazio = "";
        for (int i = 0; i < qtdDesejada-stringSize; i++) {
            vazio += " ";
        }
        return vazio;
    }
}
