package com.ingridsantos.cursomc.dto;

import com.ingridsantos.cursomc.model.Cliente;
import com.ingridsantos.cursomc.validation.ClientUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@ClientUpdate
public class ClienteDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "Campo obrigatório")
    @Length(min = 2, max = 30, message = "min 2 e max de 30 caracteres")
    private String nome;
    @NotEmpty(message = "Campo obrigatório")
    @Email
    private String email;

    public ClienteDTO(){

    }

    public ClienteDTO(Cliente obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
