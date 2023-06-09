// Generated by Ktorfit
package ktorfit

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.`internal`.*
import de.jensklingenberg.ktorfit.http.*
import dto.*

public class _IKtorFitPropietariosImpl(
  private val client: KtorfitClient,
) : IKtorFitPropietarios {
  public override suspend fun getAllPropietarios(): List<PropietarioDto> {
    val requestData = RequestData(method="GET",
        relativeUrl="propietarios",
        returnTypeData=TypeData("kotlin.collections.List",listOf(TypeData("dto.PropietarioDto")))) 

    return client.suspendRequest<List<PropietarioDto>, PropietarioDto>(requestData)!!
  }

  public override suspend fun getPropietarioById(id: Int): PropietarioDto? {
    val requestData = RequestData(method="GET",
        relativeUrl="propietarios/{id}",
        returnTypeData=TypeData("PropietarioDto?"),
        paths = listOf(PathData("id","$id",false))) 

    return client.suspendRequest<PropietarioDto?, PropietarioDto?>(requestData)
  }

  public override suspend fun savePropietario(propietario: PropietarioCreateDto):
      PropietarioCreateDto {
    val requestData = RequestData(method="POST",
        relativeUrl="propietarios",
        bodyData = propietario,
        returnTypeData=TypeData("dto.PropietarioCreateDto")) 

    return client.suspendRequest<PropietarioCreateDto, PropietarioCreateDto>(requestData)!!
  }

  public override suspend fun updatePropietario(propietario: PropietarioCreateDto):
      PropietarioCreateDto {
    val requestData = RequestData(method="PUT",
        relativeUrl="propietarios",
        bodyData = propietario,
        returnTypeData=TypeData("dto.PropietarioCreateDto")) 

    return client.suspendRequest<PropietarioCreateDto, PropietarioCreateDto>(requestData)!!
  }

  public override suspend fun deletePropietario(id: Int): Boolean {
    val requestData = RequestData(method="DELETE",
        relativeUrl="propietarios/{id}",
        returnTypeData=TypeData("kotlin.Boolean"),
        paths = listOf(PathData("id","$id",false))) 

    return client.suspendRequest<Boolean, Boolean>(requestData)!!
  }
}

public fun Ktorfit.createIKtorFitPropietarios(): IKtorFitPropietarios =
    _IKtorFitPropietariosImpl(KtorfitClient(this))
