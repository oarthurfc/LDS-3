package lds.pucminas.lab_3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lds.pucminas.lab_3.models.Vantagem;
import lds.pucminas.lab_3.repositories.VantagemRepository;

@Service        
public class VantagemService {

    @Autowired
    VantagemRepository vantagemRepository;

    public Vantagem createVantagem(Vantagem vantagem){
        return vantagemRepository.save(vantagem);
    }

    public List<Vantagem> getVantagens(){
        return vantagemRepository.findAll();
    }

    public Vantagem updateVantagem(Long id, Vantagem vantagemDetaisl){
        Vantagem vantagem = vantagemRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Vantagem não encontrada"));
        vantagem.setCusto(vantagemDetaisl.getCusto());
        vantagem.setDescricao(vantagem.getDescricao());
        vantagem.setFoto(vantagemDetaisl.getFoto());
        return vantagemRepository.save(vantagem);
    }

    public void deleteVantagem(Long id){
        vantagemRepository.deleteById(id);
    }

}
