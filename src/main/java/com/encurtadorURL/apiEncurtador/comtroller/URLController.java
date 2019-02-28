package com.encurtadorURL.apiEncurtador.comtroller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.encurtadorURL.apiEncurtador.common.ValidaURL;
import com.encurtadorURL.apiEncurtador.service.URLService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
public class URLController {

	private final URLService urlService;
	
	public URLController(URLService urlService) {
        this.urlService = urlService;
    }
	
	@RequestMapping(value = "/encurtar", method=RequestMethod.POST, consumes = {"application/json"})
    public String encurtarUrl(@RequestBody @Valid final RequisitaCurta requisitaCurta, HttpServletRequest requisicao) throws Exception {
        String urlLonga = requisitaCurta.getUrl();
        if (ValidaURL.INSTANCE.urlValida(urlLonga)) {
            String localURL = requisicao.getRequestURL().toString();
            String curtaUrl = urlService.encurtaURL(localURL, requisitaCurta.getUrl());
            return curtaUrl;
        }
        throw new Exception("Por favor, informe uma URL válida");
    }
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public RedirectView redirecionatUrl(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, Exception {
        String urlRedirecionada = urlService.getURLLongaDaID(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://" + urlRedirecionada);
        return redirectView;
    }
	
}

class RequisitaCurta{
    private String url;

    @JsonCreator
    public RequisitaCurta() {

    }

    @JsonCreator
    public RequisitaCurta(@JsonProperty("url") String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
