package tcoherenceUtil;

import org.aksw.palmetto.Coherence;
import org.aksw.palmetto.Palmetto;
import org.aksw.palmetto.corpus.CorpusAdapter;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TCoherenceCalculate {
	
	private String indexPath;
	private String calcType;
	
	public TCoherenceCalculate(String paramindexPath,String paramcalcType)
	{
		this.indexPath=paramindexPath;
		this.calcType=paramcalcType.toLowerCase();
	}
	
	/*
	 * ����һ��gibbsppout�ļ�,����avg topiccoherence
	 */
	public double TCoherenceGibbsppLDAFileInput(String fpathIn)
	{
		ArrayList<ArrayList<String>> alwordsets=new ArrayList<ArrayList<String>>(1024);//��Ϊ��1024��topic
		String wordsets[][]=null;
		try{			
			File javaldaoutFile=new File(fpathIn);
			FileInputStream fis=new FileInputStream(javaldaoutFile);
			BufferedReader brjavaldaout=new BufferedReader(new InputStreamReader(fis,"utf-8"));
			Scanner scanner=new Scanner(brjavaldaout);//ע�⣺�ո��Ƿָ���
			
			scanner.useDelimiter(" |\r\n|\n");
			//�������˫���ұ�
			ArrayList<String> alawordset=null;
			while(scanner.hasNext())
			{
				String theline=scanner.nextLine();
				
				if(theline.charAt(0)=='T')//�µ�һ��topic
				{
					if(theline.charAt(6)!='0')
						alwordsets.add(alawordset);
					alawordset=new ArrayList<String>(5120);//Ĭ��5120��words
				}
				else//ԭ�ȵ�topic
				{
					theline=theline.substring(1);//��ȥ��һ���ַ�
					String topicword=theline.split("   ")[0];
					alawordset.add(topicword);
				}
			}
			alwordsets.add(alawordset);
			
			wordsets=new String[alwordsets.size()][];
			for(int i=0;i<alwordsets.size();++i)
			{
				ArrayList<String> altopici=alwordsets.get(i);
				int lentopici=altopici.size();
				wordsets[i]=new String[lentopici];
				for(int j=0;j<lentopici;++j)
				{
					wordsets[i][j]=altopici.get(j);
				}
			}
			
			scanner.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for(int i=0;i<wordsets.length;++i)
		{
			int wordsetilength=wordsets[i].length;
			for(int j=0;j<wordsetilength;++j)
			{
				System.out.print(wordsets[i][j]+" ");
			}
			System.out.println("");
		}
		
		CorpusAdapter corpusAdapter = Palmetto.getCorpusAdapter(this.calcType,this.indexPath);
		Coherence coherence = Palmetto.getCoherence(calcType, corpusAdapter);

        double coherences[] = coherence.calculateCoherences(wordsets);
        corpusAdapter.close();		
        
        double avgcoherence=0.0;
        for(int i=0;i<coherences.length;++i)
        {
        	avgcoherence+=coherences[i];
        }
        avgcoherence=avgcoherence/coherences.length;
		return avgcoherence;		
	}	
	
	/*
	 * ����һ��bleioutput�ļ�,���� avg topiccoherence
	 */
	public double TCoherenceBleiLDAFileInput(String fpathIn)
	{
		ArrayList<ArrayList<String>> alwordsets=new ArrayList<ArrayList<String>>(1024);//��Ϊ��1024��topic
		String wordsets[][]=null;
		try{			
			File javaldaoutFile=new File(fpathIn);
			FileInputStream fis=new FileInputStream(javaldaoutFile);
			BufferedReader brjavaldaout=new BufferedReader(new InputStreamReader(fis,"utf-8"));
			Scanner scanner=new Scanner(brjavaldaout);//ע�⣺�ո��Ƿָ���
			
			scanner.useDelimiter(" |\r\n|\n");
			while(scanner.hasNext())
			{
				String topicLine=scanner.nextLine().toLowerCase();
				String words[]=topicLine.split(" ");
				ArrayList<String> alawordset=new ArrayList<String>(5120);//Ĭ��5120��words
				for(int i=2;i<words.length;++i)
				{
					alawordset.add(words[i]);
				}
				alwordsets.add(alawordset);
			}
			wordsets=new String[alwordsets.size()][];
			for(int i=0;i<alwordsets.size();++i)
			{
				ArrayList<String> altopici=alwordsets.get(i);
				int lentopici=altopici.size();
				wordsets[i]=new String[lentopici];
				for(int j=0;j<lentopici;++j)
				{
					wordsets[i][j]=altopici.get(j);
				}
			}
			
			scanner.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for(int i=0;i<wordsets.length;++i)
		{
			int wordsetilength=wordsets[i].length;
			for(int j=0;j<wordsetilength;++j)
			{
				;//System.out.print(wordsets[i][j]+" ");
			}
			//System.out.println("");
		}
		
		CorpusAdapter corpusAdapter = Palmetto.getCorpusAdapter(this.calcType,this.indexPath);
		Coherence coherence = Palmetto.getCoherence(calcType, corpusAdapter);

        double coherences[] = coherence.calculateCoherences(wordsets);
        corpusAdapter.close();
        
        double avgcoherence=0.0;
        for(int i=0;i<coherences.length;++i)
        {
        	avgcoherence+=coherences[i];
        }
        avgcoherence=avgcoherence/coherences.length;
		return avgcoherence;		
	}
	
	
	/*
	 * ����һ��javaoutput�ļ�,����avg topiccoherence
	 */
	public double TCoherenceJavaLDAFileInput(String fpathIn)
	{
		ArrayList<ArrayList<String>> alwordsets=new ArrayList<ArrayList<String>>(1024);//��Ϊ��1024��topic
		String wordsets[][]=null;
		try{			
			File javaldaoutFile=new File(fpathIn);
			FileInputStream fis=new FileInputStream(javaldaoutFile);
			BufferedReader brjavaldaout=new BufferedReader(new InputStreamReader(fis,"utf-8"));
			Scanner scanner=new Scanner(brjavaldaout);//ע�⣺�ո��Ƿָ���
			scanner.nextLine();
			scanner.nextLine();
			scanner.nextLine();
			scanner.nextLine();
			
			scanner.useDelimiter(" |\r\n");
			while(scanner.hasNext())
			{
				String topicLine=scanner.nextLine().toLowerCase();
				String words[]=topicLine.split(" ");
				ArrayList<String> alawordset=new ArrayList<String>(5120);//Ĭ��5120��words
				for(int i=1;i<words.length;++i)
				{
					alawordset.add(words[i]);
				}
				alwordsets.add(alawordset);
				scanner.nextLine();
			}
			wordsets=new String[alwordsets.size()][];
			for(int i=0;i<alwordsets.size();++i)
			{
				ArrayList<String> altopici=alwordsets.get(i);
				int lentopici=altopici.size();
				wordsets[i]=new String[lentopici];
				for(int j=0;j<lentopici;++j)
				{
					wordsets[i][j]=altopici.get(j);
				}
			}
			
			scanner.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for(int i=0;i<wordsets.length;++i)
		{
			int wordsetilength=wordsets[i].length;
			for(int j=0;j<wordsetilength;++j)
			{
				System.out.print(wordsets[i][j]+" ");
			}
			System.out.println("");
		}
		
		CorpusAdapter corpusAdapter = Palmetto.getCorpusAdapter(this.calcType,this.indexPath);
		Coherence coherence = Palmetto.getCoherence(calcType, corpusAdapter);

        double coherences[] = coherence.calculateCoherences(wordsets);
        corpusAdapter.close();		
        
        double avgcoherence=0.0;
        for(int i=0;i<coherences.length;++i)
        {
        	System.out.println(coherences[i]);
        	avgcoherence+=coherences[i];
        }
        avgcoherence=avgcoherence/coherences.length;
		return avgcoherence;
	}
	
	/*
	 * ���������鵥���鷵��topical coherence
	 */
	public double TCoherenceWordsetsInput(String wordsets[][])
	{
		CorpusAdapter corpusAdapter = Palmetto.getCorpusAdapter(this.calcType,this.indexPath);
		Coherence coherence = Palmetto.getCoherence(calcType, corpusAdapter);

        double coherences[] = coherence.calculateCoherences(wordsets);
        corpusAdapter.close();
        
        double avgcoherence=0.0;
        for(int i=0;i<coherences.length;++i)
        {
        	avgcoherence+=coherences[i];
        }
        avgcoherence=avgcoherence/coherences.length;
		return avgcoherence;
	}
	
	/*
	 * ���뵥���鷵��topical coherence
	 */
	public double TCoherenceWordsetInput(String wordset[])
	{
		String wordsets[][]=new String[1][];
		wordsets[0]=wordset;
		
		CorpusAdapter corpusAdapter = Palmetto.getCorpusAdapter(this.calcType,this.indexPath);
		Coherence coherence = Palmetto.getCoherence(calcType, corpusAdapter);

        double coherences[] = coherence.calculateCoherences(wordsets);
        corpusAdapter.close();

        return coherences[0];
	}
}
