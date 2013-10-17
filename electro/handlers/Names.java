package assets.electrolysm.electro.handlers;

import net.minecraft.item.ItemStack;
import assets.electrolysm.electro.electrolysmCore;
import assets.electrolysm.electro.advAtomics.liquids.plasma;
import assets.electrolysm.electro.block.advMachines.energiser;
import assets.electrolysm.electro.block.advMachines.injector;
import assets.electrolysm.electro.research.Research;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Names {

	public static void addName() {

		LanguageRegistry.addName(electrolysmCore.workBench, "Work Bench");
		LanguageRegistry.addName(electrolysmCore.desk, "Scientist's Desk");
		LanguageRegistry.addName(electrolysmCore.researchDesk, "Research Desk");
		LanguageRegistry.addName(electrolysmCore.blastProof, "Blast Proof Iron Block");
		LanguageRegistry.addName(electrolysmCore.blastDoor, "Blast Proof Iron Door");
		LanguageRegistry.addName(electrolysmCore.energiser, energiser.getDisplayName());
		LanguageRegistry.addName(electrolysmCore.injector, injector.getDisplayName());

		
		LanguageRegistry.addName(electrolysmCore.plasma, plasma.getName());

		
		//Items
		for(int i = 0; i < Research.researchNotes.NAMES.length; i++)
		{
			LanguageRegistry.addName(new ItemStack(electrolysmCore.researchPaper, 1, i), "Research Paper");
		}
		LanguageRegistry.addName(electrolysmCore.card, "ID Card");
		
	}

}
