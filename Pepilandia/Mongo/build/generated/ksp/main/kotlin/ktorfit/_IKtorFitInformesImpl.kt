// Generated by Ktorfit
package ktorfit

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.`internal`.*
import de.jensklingenberg.ktorfit.http.*
import dto.*

public class _IKtorFitInformesImpl(
  private val client: KtorfitClient,
) : IKtorFitInformes {
  public override suspend fun getAllInformes(): List<InformeDto> {
    val requestData = RequestData(method="GET",
        relativeUrl="informes",
        returnTypeData=TypeData("kotlin.collections.List",listOf(TypeData("dto.InformeDto")))) 

    return client.suspendRequest<List<InformeDto>, InformeDto>(requestData)!!
  }

  public override suspend fun getInformeById(id: Int): InformeDto? {
    val requestData = RequestData(method="GET",
        relativeUrl="informes/{id}",
        returnTypeData=TypeData("InformeDto?"),
        paths = listOf(PathData("id","$id",false))) 

    return client.suspendRequest<InformeDto?, InformeDto?>(requestData)
  }

  public override suspend fun saveInforme(informe: InformeCreateDto): InformeCreateDto {
    val requestData = RequestData(method="POST",
        relativeUrl="informes",
        bodyData = informe,
        returnTypeData=TypeData("dto.InformeCreateDto")) 

    return client.suspendRequest<InformeCreateDto, InformeCreateDto>(requestData)!!
  }

  public override suspend fun updateInforme(informe: InformeCreateDto): InformeCreateDto {
    val requestData = RequestData(method="PUT",
        relativeUrl="informes",
        bodyData = informe,
        returnTypeData=TypeData("dto.InformeCreateDto")) 

    return client.suspendRequest<InformeCreateDto, InformeCreateDto>(requestData)!!
  }

  public override suspend fun deleteInforme(id: Int): Boolean {
    val requestData = RequestData(method="DELETE",
        relativeUrl="informes/{id}",
        returnTypeData=TypeData("kotlin.Boolean"),
        paths = listOf(PathData("id","$id",false))) 

    return client.suspendRequest<Boolean, Boolean>(requestData)!!
  }
}

public fun Ktorfit.createIKtorFitInformes(): IKtorFitInformes =
    _IKtorFitInformesImpl(KtorfitClient(this))
