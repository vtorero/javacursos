/** TODOS **/
SELECT *  FROM public.mv_asistencia_tecnica;


/** TOTAL PPRRD x DISTRITOS**/
SELECT dpto,provincia,distrito,count(*) total FROM public.mv_asistencia_tecnica WHERE  instrumentos_acronimo='EVAR'
and estados IN('CULMINADO') group BY 1,2,3 ORDER BY 1,TOTAL DESC;

/** TOTAL EVAR x DISTRITOS**/
SELECT dpto,provincia,distrito,estados,* FROM public.mv_asistencia_tecnica WHERE  instrumentos_acronimo='EVAR'
AND estados in('EN PROCESO','CULMINADO')






