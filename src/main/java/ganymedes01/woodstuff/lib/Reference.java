package ganymedes01.woodstuff.lib;

public class Reference {

	public static final String MOD_ID = "woodstuff";
	public static final String MOD_NAME = "WoodStuff";
	public static final String DEPENDENCIES = "required-after:Forge@[10.13.4.1448,);";
	public static final String VERSION_NUMBER = "1.2.1";

	public static final String ITEM_BLOCK_TEXTURE_PATH = MOD_ID + ":";
	public static final String ENTITY_TEXTURE_PATH = ITEM_BLOCK_TEXTURE_PATH + "textures/entities/";

	public static final String SERVER_PROXY_CLASS = "ganymedes01.woodstuff.proxy.CommonProxy";
	public static final String CLIENT_PROXY_CLASS = "ganymedes01.woodstuff.proxy.ClientProxy";
}