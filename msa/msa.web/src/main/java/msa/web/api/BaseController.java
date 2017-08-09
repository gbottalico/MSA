package msa.web.api;

import java.text.SimpleDateFormat;


public abstract class BaseController extends GlobalExceptionHandler{

	protected SimpleDateFormat dateFormatDDMMYYYY = new SimpleDateFormat("ddMMyyyy");


}
