import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Usuario } from './usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlBase="http://localhost:4000/user";

  constructor(private clientehttp:HttpClient) { }

  obtenerUsuarios():Observable<Usuario[]>{
    return this.clientehttp.get<Usuario[]>(this.urlBase+"/users");

  }

  agregarUsuario(usuario: Usuario):Observable<Object>{

    return this.clientehttp.post(this.urlBase+"/user",usuario);
  }

  obtenerUsuarioPorId(id:number){
    return this.clientehttp.get<Usuario>(`${this.urlBase}/user/${id}`);
  }

  editarrUsuario(usuario: Usuario,id:number):Observable<Object>{

    return this.clientehttp.put(this.urlBase+"/user/"+id,usuario);
  }

  eliminarUsuario(id:number){
    return this.clientehttp.delete(`${this.urlBase}/user/${id}`);
  }

}