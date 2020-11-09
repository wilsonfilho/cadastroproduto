package br.com.techeval.produtos.service;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.techeval.produtos.model.Produtos;
import br.com.techeval.produtos.sender.Sender;

@Service
public class SenderServiceImpl implements SenderService{
    private final Sender sender;

    @Autowired
    public SenderServiceImpl(Sender sender){
        this.sender = sender;
    }

    
    public void sendAdded(Produtos produto){
        String log = buildLogMessage(produto, "ADDED");

        sender.send(log);
    }

    private String buildLogMessage(Produtos produto, String acao){
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append(getDateTime());
        strBuilder.append(" --> ");
        strBuilder.append(produto.getNome());
        strBuilder.append(" ");
        strBuilder.append("Ação: ");
        strBuilder.append(acao);

        return strBuilder.toString();
    }

    private String getDateTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return localDateTime.format(formatter);
    }


}
