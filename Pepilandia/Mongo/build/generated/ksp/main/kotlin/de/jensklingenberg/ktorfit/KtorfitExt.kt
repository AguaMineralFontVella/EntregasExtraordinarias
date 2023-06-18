// Generated by Ktorfit
package de.jensklingenberg.ktorfit

import ktorfit.createIKtorFitCitas
import ktorfit.createIKtorFitInformes
import ktorfit.createIKtorFitPropietarios
import ktorfit.createIKtorFitTrabajadores
import ktorfit.createIKtorFitVehiculos

public inline fun <reified T> Ktorfit.create(): T = when(T::class){
  ktorfit.IKtorFitCitas::class ->{
      this.createIKtorFitCitas() as T
      }
      ktorfit.IKtorFitInformes::class ->{
      this.createIKtorFitInformes() as T
      }
      ktorfit.IKtorFitPropietarios::class ->{
      this.createIKtorFitPropietarios() as T
      }
      ktorfit.IKtorFitTrabajadores::class ->{
      this.createIKtorFitTrabajadores() as T
      }
      ktorfit.IKtorFitVehiculos::class ->{
      this.createIKtorFitVehiculos() as T
      }

  else ->{
  throw IllegalArgumentException("Could not find any Ktorfit annotations in class"+
      T::class.qualifiedName  )
  }
}
