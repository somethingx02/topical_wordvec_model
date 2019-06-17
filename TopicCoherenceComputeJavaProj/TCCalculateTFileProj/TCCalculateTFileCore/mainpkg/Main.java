package mainpkg;

import org.aksw.palmetto.Coherence;
import org.aksw.palmetto.Palmetto;
import org.aksw.palmetto.corpus.CorpusAdapter;
import tcoherenceUtil.TCoherenceCalculate;

public class Main {
	public static void main(String args[])
	{
		// specify the wikipedia_bd (downloaded from the website)
		TCoherenceCalculate tCoherenceCalculate=new TCoherenceCalculate("/data/zlx/java_workspaces/wikipedia_bd","C_V");

		//double tcavg=tCoherenceCalculate.TCoherenceBleiLDAFileInput("/data1/zlx2/Baselines_JTW/ste/steTopicMatrix100words.txt");
		//System.out.println(tcavg);
		//tcavg=tCoherenceCalculate.TCoherenceBleiLDAFileInput("/data1/zlx2/Baselines_JTW/ste/steTopicMatrix150words.txt");
		//System.out.println(tcavg);
		//tcavg=tCoherenceCalculate.TCoherenceBleiLDAFileInput("/data1/zlx2/Baselines_JTW/ste/steTopicMatrix200words.txt");
		//System.out.println(tcavg);

	    double tcavg=tCoherenceCalculate.TCoherenceBleiLDAFileInput("/data1/zlx2/topical_wordvec_models_mirror3/save/11/jtwTopicMatrix.txt");
		System.out.println(tcavg);
        
	}
}
