package org.koin.ksp.generated

import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.*

public fun KoinApplication.defaultModule(): KoinApplication = modules(defaultModule)
public val defaultModule : Module = module {
	single() { cache.citaCache.CitaCache() } bind(cache.citaCache.ICitaCache::class)
	single() { cache.informeCache.InformeCache() } bind(cache.informeCache.IInformeCache::class)
	single() { cache.propietarioCache.PropietarioCache() } bind(cache.propietarioCache.IPropietarioCache::class)
	single() { cache.trabajadorCache.TrabajadorCache() } bind(cache.trabajadorCache.ITrabajadorCache::class)
	single() { cache.vehiculoCache.VehiculoCache() } bind(cache.vehiculoCache.IVehiculoCache::class)
	single() { controller.AppController(get(qualifier=org.koin.core.qualifier.StringQualifier("CitaCachedRepository")),get(qualifier=org.koin.core.qualifier.StringQualifier("InformeCachedRepository")),get(qualifier=org.koin.core.qualifier.StringQualifier("PropietarioCachedRepository")),get(qualifier=org.koin.core.qualifier.StringQualifier("TrabajadorCachedRepository")),get(qualifier=org.koin.core.qualifier.StringQualifier("VehiculoCachedRepository"))) } 
	single() { monitor.CitaMonitor() } 
	single(qualifier=org.koin.core.qualifier.StringQualifier("CitaCachedRepository")) { repository.citaRepository.CitaCachedRepository(get(qualifier=org.koin.core.qualifier.StringQualifier("CitaRepository")),get()) } bind(repository.citaRepository.ICitaRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("CitaRepository")) { repository.citaRepository.CitaRepository() } bind(repository.citaRepository.ICitaRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("InformeCachedRepository")) { repository.informeRepository.InformeCachedRepository(get(qualifier=org.koin.core.qualifier.StringQualifier("InformeRepository")),get()) } bind(repository.informeRepository.IInformeRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("InformeRepository")) { repository.informeRepository.InformeRepository() } bind(repository.informeRepository.IInformeRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("PropietarioCachedRepository")) { repository.propietarioRepository.PropietarioCachedRepository(get(qualifier=org.koin.core.qualifier.StringQualifier("PropietarioRepository")),get()) } bind(repository.propietarioRepository.IPropietarioRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("PropietarioRepository")) { repository.propietarioRepository.PropietarioRepository() } bind(repository.propietarioRepository.IPropietarioRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("TrabajadorCachedRepository")) { repository.trabajadorRepository.TrabajadorCachedRepository(get(qualifier=org.koin.core.qualifier.StringQualifier("TrabajadorRepository")),get()) } bind(repository.trabajadorRepository.ITrabajadorRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("TrabajadorRepository")) { repository.trabajadorRepository.TrabajadorRepository() } bind(repository.trabajadorRepository.ITrabajadorRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("VehiculoCachedRepository")) { repository.vehiculoRepository.VehiculoCachedRepository(get(qualifier=org.koin.core.qualifier.StringQualifier("VehiculoRepository")),get()) } bind(repository.vehiculoRepository.IVehiculoRepository::class)
	single(qualifier=org.koin.core.qualifier.StringQualifier("VehiculoRepository")) { repository.vehiculoRepository.VehiculoRepository() } bind(repository.vehiculoRepository.IVehiculoRepository::class)
}