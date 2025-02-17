--QUERY GENERAL
SELECT id,periodo,fecha_inicial,fecha_final,
fecha_inicial_at_actividades,
fecha_final_at_actividades,
distribucion,nivel_gobiernos_id,
nivel_gobiernos
nivel_gobiernos_id0,
nivel_gobiernos0,
nivel_gobiernos1,
nivel_gobiernos2,
tipo_entidades_id,
tipo_entidades,
departamentos_id,
provincias_id,
distritos_id,
ubigeo,
dpto,
provincia,
distrito,
entidades_id,
entidades,
entidades_etiqueta,
entidades_acronimo,
users_id,
especialistas,
especialistas_etiqueta,
instrumentos_id,
instrumentos_acronimo,
instrumentos,
estado_asistencias_id,
estados,
fases_id,
fases,
categoria_instrumento,
peligro_priorizado,
archivo_nombre,
fecha_aprobacion,
anio_inicial,
anio_final,
numero_documento,
url
observacion,
updated_at
num_sesiones,
num_actividades,
instrumento_vigente,
ht_anio,
ht_numero,
cargo_laborales_id,
cargo_etiqueta,
cargo_reporte_etiqueta,
subgrupo_etiqueta,
archivo_nombre_doc_eq_trabajo,
numero_documento_doc_eq_trabajo,
fecha_aprobacion_doc_eq_trabajo
FROM public.mv_asistencia_tecnica;

--cantidad de asistencia tecnica por año
SELECT EXTRACT(YEAR FROM fecha_inicial) AS anio, COUNT(*) AS total_asistencias
FROM public.mv_asistencia_tecnica
GROUP BY anio
ORDER BY anio;

--cantidad de asistencia tecnica por año y mes
SELECT EXTRACT(YEAR FROM fecha_inicial) AS anio,
    TO_CHAR(fecha_inicial::timestamp with time zone, 'Month') AS mes,
    COUNT(*) AS total_asistencias
FROM public.mv_asistencia_tecnica
GROUP BY anio, mes, EXTRACT(MONTH FROM fecha_inicial)
ORDER BY anio ,EXTRACT(MONTH FROM fecha_inicial);



--cantidad de asistencias por departamento
SELECT dpto, COUNT(*) AS total_asistencias
FROM public.mv_asistencia_tecnica
WHERE dpto IS NOT NULL
GROUP BY dpto
ORDER BY total_asistencias DESC;

-- cantidad de asistencias por nivel de gobierno

SELECT nivel_gobiernos,  COUNT(*) AS total_asistencias
FROM public.mv_asistencia_tecnica
WHERE nivel_gobiernos IS NOT NULL AND dpto IS NOT NULL
GROUP BY nivel_gobiernos
ORDER BY  total_asistencias DESC;



-- Cantidad de asistencias por nivel de gobierno y departamento
SELECT nivel_gobiernos, dpto, COUNT(*) AS total_asistencias
FROM public.mv_asistencia_tecnica
WHERE nivel_gobiernos IS NOT NULL AND dpto IS NOT NULL
GROUP BY nivel_gobiernos, dpto
ORDER BY nivel_gobiernos, total_asistencias DESC;


--Cantidad de asistencias por tipo de entidad
SELECT tipo_entidades, COUNT(*) AS total_asistencias
FROM public.mv_asistencia_tecnica
WHERE tipo_entidades IS NOT NULL
GROUP BY tipo_entidades
ORDER BY total_asistencias DESC;

--especialistas mas activos en asistencias
SELECT especialistas, COUNT(*) AS total_asistencias
FROM public.mv_asistencia_tecnica
WHERE especialistas IS NOT NULL
GROUP BY especialistas
ORDER BY total_asistencias DESC
LIMIT 10;

--Distribución de asistencias por estado
SELECT estados, COUNT(*) AS total_asistencias
FROM public.mv_asistencia_tecnica
WHERE estados IS NOT NULL
GROUP BY estados
ORDER BY total_asistencias DESC;

--Relación entre fases y estados de asistencia
SELECT fases, estados, COUNT(*) AS total_asistencias
FROM public.mv_asistencia_tecnica
WHERE fases IS NOT NULL AND estados IS NOT NULL
GROUP BY fases, estados
ORDER BY fases, total_asistencias DESC;

--Frecuencia de uso de instrumentos
SELECT instrumentos_acronimo, COUNT(*) AS total_usos
FROM public.mv_asistencia_tecnica
WHERE instrumentos_acronimo IS NOT NULL  
GROUP BY instrumentos_acronimo
ORDER BY total_usos DESC;

--Distribución de categorías de instrumentos
SELECT categoria_instrumento, COUNT(*) AS total_asistencias
FROM public.mv_asistencia_tecnica
WHERE categoria_instrumento IS NOT NULL
GROUP BY categoria_instrumento
ORDER BY total_asistencias DESC;


-- Número de actividades y sesiones por entidad
SELECT entidades, 
       SUM(COALESCE(num_actividades, 0)) AS total_actividades, 
       SUM(COALESCE(num_sesiones, 0)) AS total_sesiones
FROM public.mv_asistencia_tecnica
WHERE entidades IS NOT NULL
GROUP BY entidades
ORDER BY total_actividades DESC;



-- Relación entre sesiones y estado de la asistencia
SELECT estados, SUM(COALESCE(num_sesiones, 0)) AS total_sesiones
FROM public.mv_asistencia_tecnica
WHERE estados IS NOT NULL
GROUP BY estados
ORDER BY total_sesiones DESC;

-- Evolución de documentos aprobados por año
SELECT EXTRACT(YEAR FROM fecha_aprobacion) AS anio, COUNT(*) AS total_documentos_aprobados
FROM public.mv_asistencia_tecnica
WHERE fecha_aprobacion IS NOT NULL
GROUP BY anio
ORDER BY anio;

--Total de instrumentos vigentes Y caducados
SELECT instrumento_vigente,COUNT(*) AS total_vigentes
FROM public.mv_asistencia_tecnica
WHERE instrumento_vigente  IN('VIGENTE','CADUCADO') GROUP BY 1

-- total por distribciones
SELECT distribucion,count(*) total 
FROM public.mv_asistencia_tecnica group by 1 order by total desc


