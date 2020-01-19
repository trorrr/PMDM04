package cursoandroid.cursoandroid.tenda_virtual;

public class Pedido {

    int ID;
    String categoria;
    String producto;
    int cantidad;
    String direccion;
    String cidade;
    int cp;
    String usuario;
    String estado;
    boolean editable;

    public int getID() {
        return ID;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCidade() {
        return cidade;
    }

    public int getCp() {
        return cp;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getEstado() {
        return estado;
    }

    public boolean getEditable() {
        return editable;
    }



    public Pedido(int ID, String categoria, String producto, int cantidad, String direccion, String cidade, int cp, String usuario, String estado, boolean editable) {
        this.ID = ID;
        this.categoria = categoria;
        this.producto = producto;
        this.cantidad = cantidad;
        this.direccion = direccion;
        this.cidade = cidade;
        this.usuario = usuario;
        this.estado = estado;
        this.editable = editable;

    }

}
