import { Component } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usuarios-lista',
  templateUrl: './usuarios-lista.component.html'
})
export class UsuariosListaComponent {
usuarios:Usuario[];

constructor(private usuarioService:UsuarioService,private enRuta:Router){}

ngOnInit(){
  this.obtenerUsuarios();
  
}

private obtenerUsuarios(){
  this.usuarioService.obtenerUsuarios().subscribe(
    (datos)=>{
      this.usuarios=datos;
      console.log("ss",this.usuarios);
    })
}

editarUsuario(id:number){
this.enRuta.navigate(['editar-usuario',id]);
}

eliminarUsuario(id:number){
  this.usuarioService.eliminarUsuario(id).subscribe({
    next:(d)=>{this.obtenerUsuarios()},
    error:(error:any)=>{console.log(error)}
  })
}

}
