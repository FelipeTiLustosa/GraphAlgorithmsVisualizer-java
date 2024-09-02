package model.entities;

import java.util.Objects;

public class Aresta {
    private int destino;
    private int peso;

    public Aresta() {
    }

    public Aresta(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aresta aresta = (Aresta) o;
        return destino == aresta.destino && peso == aresta.peso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(destino, peso);
    }

    @Override
    public String toString() {
        return "Aresta{" +
                "destino=" + destino +
                ", peso=" + peso +
                '}';
    }
}
