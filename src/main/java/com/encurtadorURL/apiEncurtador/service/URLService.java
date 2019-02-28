package com.encurtadorURL.apiEncurtador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encurtadorURL.apiEncurtador.common.ConverterID;
import com.encurtadorURL.apiEncurtador.repository.URLRepository;

@Service
public class URLService {

    private final URLRepository urlRepository;

    @Autowired
    public URLService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String encurtaURL(String urlLocal, String urlLonga) {
        Long id = urlRepository.incrementID();
        String idUnica = ConverterID.INSTANCE.criarIDUnica(id);
        urlRepository.salvarUrl("url:"+id, urlLonga);
        String base = urlLocalDaCurta(urlLocal);
        String urlCurta = base + idUnica;
        return urlCurta;
    }

    public String getURLLongaDaID(String idUnica) throws Exception {
        Long chave = ConverterID.INSTANCE.obterChaveIdUnica(idUnica);
        String urlLonga = urlRepository.getUrl(chave);
        return urlLonga;
    }

    private String urlLocalDaCurta(String urlLocal) {
        String[] compEndereco = urlLocal.split("/");
        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i<compEndereco.length-1; i++) {
            sb.append(compEndereco[i]);
        }
        sb.append('/');
        return sb.toString();
    }

}
