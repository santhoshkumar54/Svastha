package com.svastha.util;

public class Constants {
	
	public static String AFFARMERFARMS = " Farmer";
	
	public static String AFFARMERLAND = " Land Details";
	
	public static String AFFARMERPLOT = " Plot Details";
	
	public static String AFFARMERWATER = " Water Source";
	
	public static String AFFARMERLIVE = " Live Stocks";
	
	public static String AFFARMERTOOLS = " Agriculture Equipments";
	
	public static String AFFARMERMARKET = " Grain Market";
	
	public static String AFFARMERWORKER = " Worker Details";
	
	public static String AFMRLIRRIGATION = " Irrigation Details";
	
	public static String AFMRLIMAGE = " Images";
	
	public static String AFMRSEEDL = " Seed Treatment";
	
	public static String AFMRLDSR = " DSR Methhod";
	
	public static String AFMRLSOWING = " Sowing Data";
	
	public static String AFMRL = " MRL Project";
	
	public static String AFNURSERYSEEDTREATMENT = " Nursery Seed Treatment";
	
	public static String AFNURSERYNUTRIENT = " Nursery Nutrients";

	public static String AFNURSERYWEED = " Nursery Weed Management";
	
	
	
	public static String ATADDED = "Added";
	
	public static String ATUPDATED = "Updated";
	
	public static String ATDELETED = "Deleted";
	
	
	
	public static String ASMRL = "MRL Project";
	
	public static String ASFARM = "Farmer";

	
	

	

	enum MrlProjectEnums
	{
		NurserySeedTreatment,NurseryDsrMethod,NurserySowingMethod,NurseryWater,NurseryWeed,NUrseryNUtrient,NurseryPestAndDisease
	}
	
	enum ActivityType
	{
		Added,Updated,Deleted,AddedImages,Created,Enrolled
	}
	
	enum ActivitySection
	{
		Farmer,MRLProject,OrganicProject
	}
}
