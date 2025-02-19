package com.ufvmobile.pvanet.network;

import com.ufvmobile.pvanet.domain.model.PvanetCalendar;
import com.ufvmobile.pvanet.domain.model.Assignment;
import com.ufvmobile.pvanet.domain.model.Content;
import com.ufvmobile.pvanet.domain.model.Course;
import com.ufvmobile.pvanet.domain.model.Module;
import com.ufvmobile.pvanet.domain.model.News;
import com.ufvmobile.pvanet.domain.model.ScheduleOld;
import com.ufvmobile.pvanet.domain.model.Student;
import com.ufvmobile.pvanet.domain.model.Tool;
import com.ufvmobile.pvanet.domain.model.Topic;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Interface used to get all endpoints needed to connect with the Restful API
 */
public interface PvanetApiEndPointInterface {

    String headerAuth = "Authorization";
    String headerUserPassword = "X-UserPassword";

    //TODO: adicionar o que os métodos retornam


    @GET("alunos/{sapiensRegistration}/disciplinas/")
    Observable<ArrayList<Course>> getCoursesObservable(@Path("sapiensRegistration") String sapiensRegistration,
                                             @Header(headerAuth) String auth,
                                             @Header(headerUserPassword) String password);


    /**
     *
     * @param sapiensRegistration the {@link Student#matriculaSapiens} of the {@link Student}
     * @param auth the "Authorization" value of the header
     * @param password the "X-UserPassword" value of the header
     * @return an endpoint to get the student data
     */
    @GET("alunos/{sapiensRegistration}/")
    Call<Student> getStudent(@Path("sapiensRegistration") String sapiensRegistration,
                             @Header(headerAuth) String auth,
                             @Header(headerUserPassword) String password);

    /**
     *
     * @param sapiensRegistration the {@link Student#matriculaSapiens} of the student
     * @param auth the "Authorization" value of the header
     * @param password the "X-UserPassword" value of the header
     * @return an endpoint to get the student courses
     */
    @GET("alunos/{sapiensRegistration}/disciplinas/")
    Call<ArrayList<Course>> getCourses(@Path("sapiensRegistration") String sapiensRegistration,
                                       @Header(headerAuth) String auth,
                                       @Header(headerUserPassword) String password);

    /**
     *
     * @param sapiensRegistration the {@link Student#matriculaSapiens} of the {@link Student}
     * @param courseId the {@link Course#codigoDisciplina} of the {@link Course}
     * @param auth the "Authorization" value of the header
     * @param password the "X-UserPassword" value of the header
     * @return an endpoint to get the student assignments
     */
    @Deprecated
    @GET("alunos/{sapiensRegistration}/disciplinas/{courseId}/atividades/")
    Call<ArrayList<Assignment>> getAssignments(@Path("sapiensRegistration") String sapiensRegistration,
                                               @Path("courseId") int courseId,
                                               @Header(headerAuth) String auth,
                                               @Header(headerUserPassword) String password);

    /**
     *
     * @param sapiensRegistration the {@link Student#matriculaSapiens} of the {@link Student}
     * @param courseId the {@link Course#codigoDisciplina} of the {@link Course}
     * @param auth the "Authorization" value of the header
     * @param password the "X-UserPassword" value of the header
     * @return an endpoint to get the news from the courses of a student
     */
    @GET("alunos/{sapiensRegistration}/disciplinas/{courseId}/noticias/")
    Call<ArrayList<News>> getNews(@Path("sapiensRegistration") String sapiensRegistration,
                                  @Path("courseId") int courseId,
                                  @Header(headerAuth) String auth,
                                  @Header(headerUserPassword) String password);

    /**
     *
     * @param sapiensRegistration the {@link Student#matriculaSapiens} of the {@link Student}
     * @param courseId the {@link Course#codigoDisciplina} of the {@link Course}
     * @param auth the "Authorization" value of the header
     * @param password the "X-UserPassword" value of the header
     * @return an endpoint to get the schedule of the student
     */
    @Deprecated
    @GET("alunos/{sapiensRegistration}/disciplinas/{courseId}/agendamentos/")
    Call<ArrayList<ScheduleOld>> getCalendar(@Path("sapiensRegistration") String sapiensRegistration,
                                             @Path("courseId") int courseId,
                                             @Header(headerAuth) String auth,
                                             @Header(headerUserPassword) String password);

    /**
     *
     * @param sapiensRegistration the {@link Student#matriculaSapiens} of the {@link Student}
     * @param courseId the {@link Course#codigoDisciplina} of the {@link Course}
     * @param auth the "Authorization" value of the header
     * @param password the "X-UserPassword" value of the header
     * @return an endpoint to get the modules from a given course
     */
    @GET("alunos/{sapiensRegistration}/disciplinas/{courseId}/modulos/")
    Call<ArrayList<Module>> getModules(@Path("sapiensRegistration") String sapiensRegistration,
                                       @Path("courseId") int courseId,
                                       @Header(headerAuth) String auth,
                                       @Header(headerUserPassword) String password);

    /**
     *
     * @param sapiensRegistration the {@link Student#matriculaSapiens} of the {@link Student}
     * @param courseId the {@link Course#codigoDisciplina} of the {@link Course}
     * @param moduleId the {@link Module#codigoModulo} of the {@link Module}
     * @param auth the "Authorization" value of the header
     * @param password the "X-UserPassword" value of the header
     * @return an endpoint to get the tools from a given module
     */
    @GET("alunos/{sapiensRegistration}/disciplinas/{courseId}/modulos/{moduleId}/ferramentas/")
    Call<ArrayList<Tool>> getTools(@Path("sapiensRegistration") String sapiensRegistration,
                                   @Path("courseId") int courseId,
                                   @Path("moduleId") int moduleId,
                                   @Header(headerAuth) String auth,
                                   @Header(headerUserPassword) String password);

    /**
     *
     * @param sapiensRegistration the {@link Student#matriculaSapiens} of the {@link Student}
     * @param courseId the {@link Course#codigoDisciplina} of the {@link Course}
     * @param moduleId the {@link Module#codigoModulo} of the {@link Module}
     * @param toolId the {@link Tool#codigoFerramenta} of the {@link Tool}
     * @param auth the "Authorization" value of the header
     * @param password the "X-UserPassword" value of the header
     * @return an endpoint to get the topics from a given tool
     */
    @GET("alunos/{sapiensRegistration}/disciplinas/{courseId}/modulos/{moduleId}/ferramentas/{toolId}/topicos/")
    Call<ArrayList<Topic>> getTopics(@Path("sapiensRegistration") String sapiensRegistration,
                                     @Path("courseId") int courseId,
                                     @Path("moduleId") int moduleId,
                                     @Path("toolId") int toolId,
                                     @Header(headerAuth) String auth,
                                     @Header(headerUserPassword) String password);

    /**
     *
     * @param sapiensRegistration the {@link Student#matriculaSapiens} of the {@link Student}
     * @param courseId the {@link Course#codigoDisciplina} of the {@link Course}
     * @param moduleId the {@link Module#codigoModulo} of the {@link Module}
     * @param toolId the {@link Tool#codigoFerramenta} of the {@link Tool}
     * @param topicId the {@link Topic#codigo } of the {@link Topic}
     * @param auth the "Authorization" value of the header
     * @param password the "X-UserPassword" value of the header
     * @return an endpoint to get the content from a given topic
     */
    @GET("alunos/{sapiensRegistration}/disciplinas/{courseId}/modulos/{moduleId}/ferramentas/{toolId}/topicos/{topicId}/conteudos/")
    Call<ArrayList<Content>> getContent(@Path("sapiensRegistration") String sapiensRegistration,
                                        @Path("courseId") int courseId,
                                        @Path("moduleId") int moduleId,
                                        @Path("toolId") int toolId,
                                        @Path("topicId") int topicId,
                                        @Header(headerAuth) String auth,
                                        @Header(headerUserPassword) String password);



    @GET("alunos/{sapiensRegistration}/agenda")
    Call<ArrayList<PvanetCalendar>> getCalendar(@Path("sapiensRegistration") String sapiesRegistration,
                                                @Header(headerAuth) String auth,
                                                @Header(headerUserPassword) String password);



}
