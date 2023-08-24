/*
Kevin Josué Villagrán Mérida
Ejercicio #2 
Fecha de creación: 24/08/2023 1:04
Fecha de ultima modificación: 24/08/2023 17:13
*/

public class Habitacion{

    //Atributos de la habitacion
    private String numeroHabitacion;
    private int capacidadMax;
    private float precio;
    private boolean disponible = true;
    private String tipo;
    private String[] tipos = {"Estandar","Deluxe","Suite"};
    private Cliente cliente;

    //Metodos de la habitación
    public void setNumeroHabitacion(String numeroHabitacion){
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getNumeroHabitacion(){
        return numeroHabitacion;
    }

    public void setCapacidadMax(int capacidadMax){
        this.capacidadMax = capacidadMax;
    }

    public int getCapacidadMax(){
        return capacidadMax;
    }

    public void setPrecio(float precio){
        this.precio = precio;
    }

    public float getPrecio(){
        return precio;
    }

    public String getTipo(){
        return tipo;
    }

    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }

    public boolean getDisponible(){
        return disponible;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public Habitacion(String numeroHabitacion, int capacidadMax, float precio, int tipo){//Constructor
        this.numeroHabitacion = numeroHabitacion;
        this.capacidadMax = capacidadMax;
        this.precio = precio;
        this.tipo = this.tipos[tipo];
    }

    public String toString(){//Sirve para mostrar los datos de la habitacion

        if(cliente != null)
            return "Datos de la habitación: " + "\nNumero de habitación: " + numeroHabitacion + "\nCapacidad maxima: " + capacidadMax + "\nPrecio: " + precio + "\nDisponible: " + disponible + "\nTipo: " + tipo + "\nA nombre del cliente: " + cliente.getNombre();
        else
            return "Datos de la habitación: " + "\nNumero de habitación: " + numeroHabitacion + "\nCapacidad maxima: " + capacidadMax + "\nPrecio: " + precio + "\nDisponible: " + disponible + "\nTipo: " + tipo;
 
    }

    public boolean ValidarEspacio(int personas){//Sirve para validar espacio en la habitacion para asignar el cliente
        if(personas > capacidadMax)
            return false;
        else
            return true;
    }
}