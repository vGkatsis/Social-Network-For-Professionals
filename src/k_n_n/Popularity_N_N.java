package k_n_n;

import java.util.List;
import com.google.common.base.Objects;

import dbconnection.Connection_Dist;

import java.util.ArrayList;

import model.Post;
import model.Jobap;
import model.Post_Utils;
import model.Jobap_Utils;
import model.Postinterest_Utils;
import model.Postcomment_Utils;
import model.Jobapplication_Utils;

public class Popularity_N_N {

	public List <Id_Rank> popularity_nn(int k, String action, List<Integer> users, List<Integer> friends)
	{
		Connection_Dist conn_d = new Connection_Dist();
		
		List <Id_Rank> user_ranklist = new ArrayList<Id_Rank>();
		
		if(Objects.equal(action, "posts"))
		{			
			for(int i = 0; i < users.size(); i++)
			{
				Id_Rank user_rank = new Id_Rank();

				double interests = 0.0;
				double comments = 0.0;
				
				for(int j = 0; j < friends.size(); j++)
				{
					Post_Utils p_u = new Post_Utils();
					Postinterest_Utils pi_u = new Postinterest_Utils();
					Postcomment_Utils pc_u = new Postcomment_Utils();
					
					List<Post> postlist;
					
					int interest_mult = 0;
					int comments_mult = 0;
					int commented_posts = pc_u.Get_UserCommentedPostsMult(conn_d, friends.get(j));
					double com_avg = 0.0; 
					double comment_factor = 1.0;
					
					if(commented_posts > 0)
					{
						com_avg = (pc_u.Get_UserCommentsMult(conn_d,friends.get(j)) / commented_posts);
						
						if(com_avg <= 1.5)
						{
							comment_factor = 1.0;
						}
						else
						{
							comment_factor = 1.0 - (com_avg/10);
							if(comment_factor < 0.1)
							{
								comment_factor = 0.1;
							}
						}
					}
					
					postlist = p_u.Get_UserPosts(conn_d, users.get(i));
					for(int l = 0; l < postlist.size(); l++)
					{
						comments_mult += pc_u.Get_UserPostCommentsMult(conn_d, postlist.get(l).getIdposts(),friends.get(j));
						if(pi_u.CheckFor_Interest(conn_d, postlist.get(l).getIdposts(), friends.get(j)))
						{
							interest_mult++;
						}
					}
					interests  += Math.pow(interest_mult,2);
					comments += Math.pow((comments_mult * comment_factor),2);
				}
				
				interests = Math.sqrt(interests);
				comments = Math.sqrt(comments);
			
				user_rank.setId(users.get(i));
				user_rank.setRank(interests + comments);
			
				if(user_rank.getRank() > 0)
				{
					if(user_ranklist.size() < k)
					{
						user_ranklist.add(user_rank);
					}
					else
					{
						int back_index = 0;
						for(int front_index = 1; front_index < k; front_index++)
						{
							if(user_ranklist.get(front_index).getRank() < user_ranklist.get(back_index).getRank())
							{
								back_index = front_index;
							}
						}
						
						if(user_ranklist.get(back_index).getRank() < user_rank.getRank())
						{
							user_ranklist.set(back_index, user_rank);
						}
					}
				}
			}
		}	
		else if(Objects.equal(action, "ads"))
		{
			for(int i = 0; i < users.size(); i++)
			{
				Id_Rank user_rank = new Id_Rank();

				double apply = 0.0;
				
				for(int j = 0; j < friends.size(); j++)
				{
					Jobap_Utils j_u = new Jobap_Utils();
					Jobapplication_Utils ja_u = new Jobapplication_Utils();
					
					List <Jobap> aplylist;
					int apply_mult = 0;
					
					aplylist = j_u.Get_UserJobaps(conn_d, users.get(i));
					for(int l = 0; l < aplylist.size(); l++)
					{
						if(ja_u.CheckFor_Application(conn_d, aplylist.get(l).getIdjobap(), friends.get(j)))
						{
							apply_mult++;
						}
					}
					
					apply  += Math.pow(apply_mult,2);	
				}
				
				apply = Math.sqrt(apply);
				
				user_rank.setId(users.get(i));
				user_rank.setRank(apply);

				if(user_rank.getRank() > 0)
				{
					if(user_ranklist.size() < k)
					{
						user_ranklist.add(user_rank);
					}
					else
					{
						int back_index = 0;
						for(int front_index = 1; front_index < k; front_index++)
						{
							if(user_ranklist.get(front_index).getRank() < user_ranklist.get(back_index).getRank())
							{
								back_index = front_index;
							}
						}
						
						if(user_ranklist.get(back_index).getRank() < user_rank.getRank())
						{
							user_ranklist.set(back_index, user_rank);
						}
					}
				}
			}
		}
		else
		{
			user_ranklist = null;
			return user_ranklist;
		}
		
		return user_ranklist;
	}
	
}
