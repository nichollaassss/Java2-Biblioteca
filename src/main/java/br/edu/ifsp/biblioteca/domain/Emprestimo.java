package br.edu.ifsp.biblioteca.domain;

import com.sun.source.doctree.EscapeTree;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    public static final int PRAZO_EM_DIAS = 14;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exemplar_id", nullable = false)
    private Exemplar exemplar;

    @Column(name = "data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @Column(name = "data_prevista_devolucao", nullable = false)
    private LocalDate dataPrevistaDevolucao;

    @Column(name = "data_devolucao", nullable = true)
    private LocalDate dataDevolucao;

    protected Emprestimo () {}

    public Emprestimo (Usuario usuario, Exemplar exemplar, LocalDate dataEmprestimo) {
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(14);
    }

    public boolean isAtivo(){
        return this.dataDevolucao == null;
    }

    public boolean isAtrasado(LocalDate hoje){
         return this.isAtivo() && this.dataPrevistaDevolucao.isBefore(hoje);
    }

    public long diasDeAtraso(LocalDate hoje){
        if(!this.isAtrasado(hoje))
            return 0;

        return ChronoUnit.DAYS.between(this.dataPrevistaDevolucao, hoje);
    }

    public void registrarDevolucao(LocalDate dataDevolucao){
        if(!this.isAtivo())
            throw new IllegalStateException("Este empréstimo já foi devolvido");

        this.dataDevolucao = dataDevolucao;
        this.exemplar.devolver();
    }

    public long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    @Override
    public String toString(){
        return "Emprestimo #" + this.id
                + " | previsto para " + this.dataPrevistaDevolucao
                + (this.isAtivo() ? "Em aberto" : "Devolvido em: ") + this.dataPrevistaDevolucao;
    }
}