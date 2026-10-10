package Model;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class Mazo {
    private List<Carta> cartas;

    public Mazo() {
        this.cartas = new ArrayList<>();
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void agregarCartasAlMazo() {

        //CARTAS DE CATEDRÁTICO
        cartas.add(new ProfesorCartas(1, "Ing. Castellanos", 2, "Sesiones exhaustivas en donde aclara dudas, pero penaliza la falta de avance", "Matemática", 2, 90));
        cartas.add(new ProfesorCartas(2, "Dra. Hacohen", 1, "Revisa reportes de laboratorio señalando cualquier falta de citación", "Biología", 1, 30));
        cartas.add(new ProfesorCartas(3, "Ing. Barrera", 2, "Asistencia práctica en armado de cirucitos en protoboard con retroalimentación constructiva", "Ciencias de la Computación", 0, 90));
        cartas.add(new ProfesorCartas(4, "Dra. Rojas", 3, "Revisa reportes de laboratorio con retroalimentación constructiva, pero penaliza errores en el formato de presentación solicitado ", "Química", 1, 30));
        cartas.add(new ProfesorCartas(5, "Ing. Salazar", 1, "Consulta rápida sin llamadas de atención ni penalización", "Ciencias de la Computación", 0, 30));
        cartas.add(new ProfesorCartas(6, "Dr. Hernández", 2, "Explicación clara y concisa de los conceptos teóricos pero estricto al evaluar las asignaciones dejadas con observaciones por apoyarse de la IA", "Historia", 1, 60));
            

        //CARTAS DE CURSO
        // Parámetros: (id, nombre, costoEnergia, descripcion, creditos, dificultad)
        cartas.add(new CursoCartas(7, "Programación Básica", 2, "Fundamentos de algoritmos, estructuras de control y desarrollo de software", 1, 2));
        cartas.add(new CursoCartas(8, "Cálculo 1", 4, "Límites, derivadas, aplicaciones de optimización y razones de cambio e integrales", 4, 6));
        cartas.add(new CursoCartas(9, "Org. de Computadoras y Assembler", 3, "Arquitectura de microprocesadores, circuitos, algebra booleana y registros de bajo nivel", 3, 5));
        cartas.add(new CursoCartas(10, "Algoritmos y Estructuras de Datos", 5, "Árboles, grafos, recursión y análisis de complejidad computacional", 5, 7));
        cartas.add(new CursoCartas(11, "Comunicación Efectiva", 1, "Técnicas de expresión oral, redacción académica y preparación de discursos", 1, 1));
        cartas.add(new CursoCartas(12, "Programación Orientada a Objetos", 4, "Diseño con clases, encapsulamiento, herencia y polimorfismo", 5, 6));

        //CARTAS DE EVENTO DE CAMPUS
        // Parámetros: (id, nombre, costoEnergia, descripcion)
        cartas.add(new EventoCampusCartas(13, "Feria de Clubes", 1, "Actividad recreativa en el campus que despeja la mente y recarga energía", 1));
        cartas.add(new EventoCampusCartas(14, "Semana de Parciales", 4, "Jornada intensiva de exámenes continuos con alto desgaste físico pero gran avance", 4));
        cartas.add(new EventoCampusCartas(15, "Noche de Hackathon", 2, "Maratón continua de programación para entregar y validar un proyecto integrador", 2  ));
        cartas.add(new EventoCampusCartas(16, "Descanso de Medio Término", 0, "Días libres para recuperar fuerzas y ponerse al día con las asignaciones pendientes", 0));
        cartas.add(new EventoCampusCartas(17, "Falla del Portal Académico", 4, "Caída del servidor antes de medianoche que causa retrasos en la entrega de tareas", 0));
        cartas.add(new EventoCampusCartas(18, "Taller de Hábitos de Estudio", 1, "Sesión de orientación académica para optimizar el tiempo y mejorar el rendimiento", 1));

    }


    // Acciones que puede realizar el mazo dependiendo de la opción seleccionada por el jugador
    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public void removerCarta(Carta carta) {
        cartas.remove(carta);
    }

    public Carta buscarCartaPorID(int ID) {
        for (Carta carta : cartas) {
            if (carta.getID() == ID) {
                return carta;
            }
        }
        return null; // Retorna null si no se encuentra la carta
    }


    // Búsqueda por nombre ignorando mayúsculas/minúsculas y espacios sobrantes usando IgnoreCase y trim
    public Carta buscarCartaPorNombre(String nombre) {
        for (Carta carta : cartas) {
            if (carta.getNombreDescriptivo().equalsIgnoreCase(nombre.trim())) {
                return carta;
            }
        }
        return null;
    }

    // Ordenamiento natural por costo de energía (gracias al compareTo de Carta)
    public void ordenarCartasPorEnergia() {
        Collections.sort(this.cartas);
    }

    public int getCantidadCartas() {
        return cartas.size();
    }

}




    
