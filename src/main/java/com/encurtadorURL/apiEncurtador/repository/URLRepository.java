package com.encurtadorURL.apiEncurtador.repository;

import redis.clients.jedis.Jedis;
import org.springframework.stereotype.Repository;

@Repository
public class URLRepository {

    private final Jedis jedis;
    private final String idChave;
    private final String urlChave;

    public URLRepository() {
        this.jedis = new Jedis();
        this.idChave = "id";
        this.urlChave = "url:";
    }

    public URLRepository(Jedis jedis, String idChave, String urlChave) {
        this.jedis = jedis;
        this.idChave = idChave;
        this.urlChave = urlChave;
    }

    public Long incrementID() {
        Long id = jedis.incr(idChave);
        return id-1;
    }

    public void salvarUrl(String chave, String urlLonga) {
        jedis.hset(urlChave, chave, urlLonga);
    }

    public String getUrl(Long id) throws Exception {
        String url = jedis.hget(urlChave, "url:"+id);
        if (url == null) {
            throw new Exception("URL da chave " + id + " não existe");
        }
        return url;
    }
    
}
