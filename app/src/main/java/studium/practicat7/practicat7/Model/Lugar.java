package studium.practicat7.practicat7.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Lugar implements Parcelable {
    public Long id;
    public String NombreLugar;
    public String Comentarios;
    public Float Valoracion;
    public String Categoria;
    public Float Latitud;
    public Float Longitud;



    public Lugar() {
        this.id = null;
        this.NombreLugar = "";
        this.Comentarios = "";
        this.Valoracion = 0f;
        this.Categoria = "";
        this.Latitud = 0f;
        this.Longitud = 0f;
    }

    public Lugar(String NombreLugar, String Comentarios, Float Valoracion, String Categoria, Float Latitud, Float Longitud) {
        this.id = null;
        this.NombreLugar = NombreLugar;
        this.Comentarios = Comentarios;
        this.Valoracion = Valoracion;
        this.Categoria = Categoria;
        this.Latitud = Latitud;
        this.Longitud = Longitud;
    }

    public Lugar(Long id, String NombreLugar, String Comentarios, Float Valoracion, String Categoria, Float Latitud, Float Longitud) {
        this.id = id;
        this.NombreLugar = NombreLugar;
        this.Comentarios = Comentarios;
        this.Valoracion = Valoracion;
        this.Categoria = Categoria;
        this.Latitud = Latitud;
        this.Longitud = Longitud;
    }

    protected Lugar(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        NombreLugar = in.readString();
        Comentarios = in.readString();
        if (in.readByte() == 0) {
            Valoracion = null;
        } else {
            Valoracion = in.readFloat();
        }
        Categoria = in.readString();
        if (in.readByte() == 0) {
            Latitud = null;
        } else {
            Latitud = in.readFloat();
        }
        if (in.readByte() == 0) {
            Longitud = null;
        } else {
            Longitud = in.readFloat();
        }
    }

    public static final Creator<Lugar> CREATOR = new Creator<Lugar>() {
        @Override
        public Lugar createFromParcel(Parcel in) {
            return new Lugar(in);
        }

        @Override
        public Lugar[] newArray(int size) {
            return new Lugar[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return NombreLugar;
    }

    public void setNombre(String nombre) {
        this.NombreLugar = nombre;
    }

    public String getComentarios() {
        return Comentarios;
    }

    public void setComentarios(String comentarios) {
        this.Comentarios = comentarios;
    }

    public Float getValoracion() {
        return Valoracion;
    }

    public void setValoracion(Float valoracion) {
        this.Valoracion = valoracion;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        this.Categoria = categoria;
    }

    public Float getLatitud() {
        return Latitud;
    }

    public void setLatitud(Float latitud) {
        this.Latitud = latitud;
    }

    public Float getLongitud() {
        return Longitud;
    }

    public void setLongitud(Float longitud) {
        this.Longitud = longitud;
    }

    @Override
    public String toString() {
        return "Lugar {" + "id=" + id + ", NombreLugar='" + NombreLugar + '\'' + ", Comentarios='" + Comentarios + '\'' + ", Valoracion=" + Valoracion + ", Categoria='" + Categoria + '\'' + ", Latitud='" + Latitud + ", Longuitud='" + Longitud + '}';
    }


    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(NombreLugar);
        dest.writeString(Comentarios);
        dest.writeFloat(Valoracion);
        dest.writeString(Categoria);
        dest.writeFloat(Latitud);
        dest.writeFloat(Longitud);
    }
}