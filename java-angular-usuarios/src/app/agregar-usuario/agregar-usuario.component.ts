import { Component } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';
import { Router } from '@angular/router';
import { compileNgModule } from '@angular/compiler';

@Component({
  selector: 'app-agregar-usuariio',
  templateUrl: './agregar-usuario.component.html'

})
export class AgregarUsuariioComponent {
  usuario: Usuario = new Usuario();


  constructor(private usuarioServicio: UsuarioService, private enRutador: Router) { }
  
  enviar() {
    this.guardarUsuario();
  }

  guardarUsuario() {
    this.usuarioServicio.agregarUsuario(this.usuario).subscribe({
      next: (datos) => {
        this.irListaUsuarios();
      },
      error: (error: any) => {
        console.log(error)
      }
    })
  }
  
  irListaUsuarios(){
    this.enRutador.navigate(["/usuarios"]);
  }

}