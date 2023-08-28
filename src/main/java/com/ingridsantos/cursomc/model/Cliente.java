package com.ingridsantos.cursomc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ingridsantos.cursomc.enums.TipoCliente;

import javax.persistence.*;
import java.util.*;

import static com.ingridsantos.cursomc.enums.TipoCliente.toEnum;
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    private String nome;

    private String email;

    private String cpfOuCnpj;

    private Integer tipoCliente;

    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference
    private List<Endereco> enderecos = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();
    @JsonBackReference
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> Pedidos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoCliente = tipoCliente.getCodigo();
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

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public TipoCliente getTipoCliente() {
        return toEnum(tipoCliente);
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente.getCodigo();
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public List<Pedido> getPedidos() {
        return Pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        Pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpfOuCnpj='" + cpfOuCnpj + '\'' +
                ", tipoCliente=" + tipoCliente +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
