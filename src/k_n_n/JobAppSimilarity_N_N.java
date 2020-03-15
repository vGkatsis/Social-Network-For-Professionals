package k_n_n;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import dbconnection.Connection_Dist;

import model.Jobap;
import model.Pinfo;
import model.PInfo_Utils;
import model.Jobap_Utils;

public class JobAppSimilarity_N_N {

	
		public List<Id_Rank> appsimilarity_nn(int k, List<Id_Rank> users, int userid)
		{
			Connection_Dist conn_d = new Connection_Dist();
			
			Pinfo pinfo = new Pinfo();
			Jobap_Utils j_u = new Jobap_Utils();
			PInfo_Utils p_u = new PInfo_Utils();
			
			int user_wordsum = 0;
			int kuser_wordsum = 0;
			int wordsvectorMult = 0;
			
			double cosine_similarity = 0.0;
			double user_wordsvectorNorm = 0.0;
			double kuser_wordsvectorNorm = 0.0;
			
			String skills_interests;
			String user_jobap = "";
			String delimiters = "\\s+|,\\s*|\\.\\s*|\\!\\s*|\\:\\s*";
			
			List<String> user_info = new ArrayList<String>();
			List<String> kuser_app = new ArrayList<String>();
			List<String> word_pool = new ArrayList<String>();
			List<Jobap> jobap = new ArrayList<Jobap>();
			List<Id_Rank> jobapp_ranks = new ArrayList<Id_Rank>();
						
			pinfo = p_u.Get_User_Pinfo(conn_d, userid);
			skills_interests = pinfo.getMisc();
			
			if((skills_interests != null) && (!skills_interests.equals("")))
			{
				user_info = Arrays.asList(skills_interests.split(delimiters));

				for(int i = 0; i < users.size(); i++)
				{
					jobap = j_u.Get_UserJobaps(conn_d, users.get(i).getId());
					
					for(int j = 0; j < jobap.size(); j++)
					{
						user_jobap = jobap.get(j).getJobname() + jobap.get(j).getJobposition() + jobap.get(j).getJobdescription() + jobap.get(j).getJobcompany();
						kuser_app = Arrays.asList(user_jobap.split(delimiters));
						
						word_pool.addAll(user_info);
						word_pool.addAll(kuser_app);
					
						for(int l = 0; l < word_pool.size(); l++)
						{
							user_wordsum = 0;
							kuser_wordsum = 0;
							
							for(int user_index = 0; user_index < user_info.size(); user_index++)
							{
								if(word_pool.get(l).equals(user_info.get(user_index)))
								{
									user_wordsum++;
								}
							}
							
							for(int kuser_index = 0; kuser_index < kuser_app.size(); kuser_index++)
							{
								if(word_pool.get(l).equals(kuser_app.get(kuser_index)))
								{
									kuser_wordsum++;
								}
							}
						
							wordsvectorMult += user_wordsum * kuser_wordsum;
							user_wordsvectorNorm += Math.pow(user_wordsum, 2);
							kuser_wordsvectorNorm += Math.pow(kuser_wordsum, 2);
						}
					
						user_wordsvectorNorm = Math.sqrt(user_wordsvectorNorm);
						kuser_wordsvectorNorm = Math.sqrt(kuser_wordsvectorNorm);
					
						if(user_wordsvectorNorm != 0 && kuser_wordsvectorNorm != 0)
						{
							Id_Rank jobapp_rank = new Id_Rank();

							cosine_similarity = wordsvectorMult / (user_wordsvectorNorm * kuser_wordsvectorNorm);
						
							jobapp_rank.setId(jobap.get(j).getIdjobap());
							jobapp_rank.setRank(cosine_similarity);
						
							if(jobapp_ranks.size() < k)
							{
								jobapp_ranks.add(jobapp_rank);
							}
							else
							{
								int back_index = 0;
								for(int front_index = 1; front_index < k; front_index++)
								{
									if(jobapp_ranks.get(front_index).getRank() < jobapp_ranks.get(back_index).getRank())
									{
										back_index = front_index;
									}
								}
								
								if(jobapp_ranks.get(back_index).getRank() < jobapp_rank.getRank())
								{
									jobapp_ranks.set(back_index, jobapp_rank);
								}
							}
						}
						
						kuser_app = new ArrayList<String>();
						word_pool = new ArrayList<String>();
					}
				}
			}
			else
			{
				jobapp_ranks = null;
			}
			return jobapp_ranks;
		}
}
