package com.joabeandrade.todosimple.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Objects;

@Entity  //Entity significa que tem que trata essa classe como uma tabela
@Table(name = User.TABLE_NAME)  //Criação da tabela no banco de dados e nomeando como user
public class User {

    public interface CreateUser {}
    public interface UpdateUser {}

    public static final String TABLE_NAME = "user";
    @Id  //Id do usuário
    @GeneratedValue(strategy = GenerationType.IDENTITY) //É uma estratégia para gerar o número no banco de dados
    @Column(name = "id", unique = true)  //Será criada uma coluna com o nome id e valor único
    private Long id;
    @Column(name = "username", length = 100, nullable = false, unique = true)  //Será criada uma coluna com o nome username de tamanho 100 e não pode ter um valor Null.
    @NotNull(groups = CreateUser.class)  //Signica que não pode ser nulo
    @NotEmpty(groups = CreateUser.class)  //Não pode colocar uma String vazia
    @Size(groups = CreateUser.class, min = 5, max = 100) //O tamanho mínimo e máximo do username
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})  //Signica que não pode ser nulo
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})  //Não pode colocar uma String vazia
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60)  //O tamanho mínimo e máximo da senha
    private String password;

    //private List<Task>  tasks = new ArrayList<Task>();

    public User(){

    }
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (null == obj) return false;
        if (obj instanceof User) return false;
        User other = (User) obj;
        if (this.id == null){
            if (other.id != null){
                return false;
            } else if (!this.id.equals(other.id)){
                return false;
            }
        }
        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username) && Objects.equals(this.password, other.password);
    }

    /*@Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }*/

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null ? 0: this.id.hashCode()));
        return result;
    }
}
