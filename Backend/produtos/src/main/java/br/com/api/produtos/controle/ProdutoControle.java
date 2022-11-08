package br.com.api.produtos.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;
import br.com.api.produtos.servico.ProdutoServico;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProdutoControle {
    
    @Autowired
    private ProdutoRepositorio acoes;

    @Autowired
    private ProdutoServico ps;

    @RequestMapping(value="", method = RequestMethod.GET)
    public @ResponseBody String inicio(){
        return "API de produtos funcionando";
    }
/* 
    @RequestMapping(value="/produtos", method = RequestMethod.POST)
    public @ResponseBody ProdutoModelo cadastrar (@RequestBody ProdutoModelo produto){
        acoes.save(produto);
        return produto;
    }
*/

    @RequestMapping(value="/produtos", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> cadastrar (@RequestBody ProdutoModelo pm){
        
        return ps.cadastrarAlterar(pm, "cadastrar");
    }

    @RequestMapping(value="/produtos", method = RequestMethod.GET)
    public @ResponseBody Iterable<ProdutoModelo> listar (){
        //return acoes.findAll();  
        return ps.listar();      
    }

    // @RequestMapping(value="/produtos", method = RequestMethod.PUT)
    // public @ResponseBody ProdutoModelo alterar (@RequestBody ProdutoModelo produto){
    //     acoes.save(produto);
    //     return produto;
    // }

    @RequestMapping(value="/produtos", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<?> alterar (@RequestBody ProdutoModelo pm){
        return ps.cadastrarAlterar(pm, "alterar");
    }


    @RequestMapping(value="/produtos/{codigo}", method = RequestMethod.GET)
    public @ResponseBody ProdutoModelo filtrar (@PathVariable Long codigo) {
        //    return acoes.findById(codigo);  
            return acoes.findByCodigo(codigo);            
    }

    // @RequestMapping(value="/produtos/{codigo}", method = RequestMethod.DELETE)
	// public @ResponseBody RespostaModelo apagar(@PathVariable Long codigo) {
	// //	acoes.deleteById(codigo);
		
	// 	RespostaModelo resposta = new RespostaModelo();
		
	// 	try {
	// 		ProdutoModelo produto = filtrar(codigo);
	// 		acoes.delete(produto);
	// 		resposta.setMensagem("Produto removido com sucesso");
	// 	}catch(Exception erro) {
	// 		resposta.setMensagem("Falha ao remover: '"+erro.getMessage()+"'.");            
	// 	}
		
	// 	return resposta;		
	// }

    @RequestMapping(value="/produtos/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo) {
	//	acoes.deleteById(codigo);
		
		return ps.remover(codigo);		
	}


}
