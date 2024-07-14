package com.svastha.util;

public class Constants {
	
	public static String AFFARMERTOOLS = " Agriculture Equipments";
	
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
