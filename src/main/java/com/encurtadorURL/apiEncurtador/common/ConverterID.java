package com.encurtadorURL.apiEncurtador.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ConverterID {

	public static final ConverterID INSTANCE = new ConverterID();

    private ConverterID() {
    	inicializaCharParaTabelaIndex();
    	inicializaIndexParaTabelaChar();
    }

    private static HashMap<Character, Integer> charParaTabelaIndex;
    private static List<Character> indexParaTabelaChar;

    private void inicializaCharParaTabelaIndex() {
        charParaTabelaIndex = new HashMap<>();
        
        for (int i=0; i<26; i++) {
            char c='a';
            c+=i;
            charParaTabelaIndex.put(c, i);
        }
        
        for (int i=26; i<52; i++) {
            char c='A';
            c+=(i-26);
            charParaTabelaIndex.put(c, i);
        }
        
        for (int i=52; i<62; i++) {
            char c='0';
            c+=(i-52);
            charParaTabelaIndex.put(c, i);
        }
    }

    private void inicializaIndexParaTabelaChar() {
        indexParaTabelaChar = new ArrayList<>();
        
        for (int i=0; i<26; i++) {
            char c='a';
            c+=i;
            indexParaTabelaChar.add(c);
        }
        
        for (int i=26; i<52; i++) {
            char c='A';
            c+=(i-26);
            indexParaTabelaChar.add(c);
        }
        
        for (int i=52; i<62; i++) {
            char c='0';
            c+=(i-52);
            indexParaTabelaChar.add(c);
        }
    }

    public static String criarIDUnica(Long id) {
        List<Integer> idBase62 = converteIDBase10ParaBase62(id);
        StringBuilder urlIDUnica = new StringBuilder();
        for (int x: idBase62) {
        	urlIDUnica.append(indexParaTabelaChar.get(x));
        }
        return urlIDUnica.toString();
    }

    private static List<Integer> converteIDBase10ParaBase62(Long id) {
        List<Integer> listaID = new LinkedList<>();
        while(id > 0) {
            int resto = (int)(id%62);
            ((LinkedList<Integer>) listaID).addFirst(resto);
            id/=62;
        }
        return listaID;
    }

    public static Long obterChaveIdUnica(String idUnica) {
        List<Character> idsBase62 = new ArrayList<>();
        for (int i = 0; i < idUnica.length(); ++i) {
            idsBase62.add(idUnica.charAt(i));
        }
        Long chaves = converteIDBase62ParaBase10(idsBase62);
        return chaves;
    }

    private static Long converteIDBase62ParaBase10(List<Character> ids) {
        long id = 0L;
        for (int i=0, exp=(ids.size()-1); i<ids.size(); i++, exp--) {
            int base10 = charParaTabelaIndex.get(ids.get(i));
            id += (base10 * Math.pow(62.0, exp));
        }
        return id;
    }
    
}
