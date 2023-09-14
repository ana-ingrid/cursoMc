package com.ingridsantos.cursomc.dto;

import com.ingridsantos.cursomc.model.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ClienteDTO implements Serializable {

    private Integer id = null;
    @NotEmpty(message = "Campo obrigatório")
    @Length(min = 2, max = 30, message = "min 2 e max de 30 caracteres")
    private String nome;
    @NotEmpty(message = "Campo obrigatório")
    @Email
    private String email;


    public ClienteDTO(Cliente obj) {
        this.nome = obj.getNome();
        this.email = obj.getEmail();
    }

    public ClienteDTO(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}