package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;

@Service
public class ProdutoServico {
    
    @Autowired
    private ProdutoRepositorio pr;

    @Autowired
    private RespostaModelo rm;
    
    public Iterable<ProdutoModelo> listar(){
        return pr.findAll();
    }
/*
    public ResponseEntity<?> cadastrar(ProdutoModelo pm){

        if(pm.getNome().equals("")){
            rm.setMensagem("O nome do produto é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }else if(pm.getMarca().equals("")){
            rm.setMensagem("O nome da marca é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.CREATED);
        }
    }
*/
    public ResponseEntity<?> cadastrarAlterar(ProdutoModelo pm, String acao){

        if(pm.getNome().equals("")){
            rm.setMensagem("O nome do produto é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }else if(pm.getMarca().equals("")){
            rm.setMensagem("O nome da marca é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }else{
            if(acao.equals("cadastrar")){
                return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.CREATED);
            }else if(acao.equals("alterar")){
                return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.OK);
            }else{
                rm.setMensagem("Ação em desacordo");
                return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
            }
        }
    }

    public ResponseEntity<RespostaModelo> remover(long codigo){
       		
		try {
			pr.deleteById(codigo);
			rm.setMensagem("Produto removido com sucesso");
		}catch(Exception erro) {
			rm.setMensagem("Falha ao remover: '"+erro.getMessage()+"'.");            
		}
		
		return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
    }   
}
