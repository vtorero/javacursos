import { Component } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-editar-usuario',
  templateUrl: './editar-usuario.component.html'
  
})
export class EditarUsuarioComponent {
usuario:Usuario=new Usuario();
id:number;

constructor(private usuarioService:UsuarioService,private ruta:ActivatedRoute,
  private enRutador:Router
){}

ngOnInit(){

  this.id = this.ruta.snapshot.params['id'];
 this.usuarioService.obtenerUsuarioPorId(this.id).subscribe({
next:(data)=>{ this.usuario=data;
},error:(error:any)=>{console.log(error)}
 });
}

Actualizar(){
  this.editarUsuario();

}
editarUsuario(){
  this.usuarioService.editarrUsuario(this.usuario,this.id).subscribe({
    next:(data)=>{
      this.enRutador.navigate(['usuarios']);
    },
    error:(error:any)=>{console.log(error)}
 
});
}
}
