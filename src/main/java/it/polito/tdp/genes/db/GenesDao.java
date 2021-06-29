package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Genes;


public class GenesDao {
	
	public void getAllGenes(Map<String,Genes> idMap){
		String sql = "SELECT DISTINCT g.GeneID ,g.Essential, g.Chromosome "
				+ "FROM genes g "
				+ "WHERE g.Essential='Essential' "
				+"ORDER BY g.GeneID";
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				idMap.put(res.getString("GeneID"), genes);
			}
			res.close();
			st.close();
			conn.close();
			return ;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return ;
		}
	}
	
   
	public void getAllArchi(Map<String, Genes> idMap, List<Adiacenza> archi) {
		 String sql = "SELECT DISTINCT  g1.GeneID AS id1, g2.GeneID AS id2,ABS (i.Expression_Corr) AS peso\n"
		    		+ "FROM genes g1,genes g2,interactions i\n"
		    		+ "WHERE g1.Essential='Essential' AND g2.Essential=g1.Essential\n"
		    		+ "AND i.GeneID1 =g1.GeneID AND i.GeneID2=g2.GeneID\n"
		    		+ "AND g1.GeneID <> g2.GeneID";
			Connection conn = DBConnect.getConnection();

			try {
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet res = st.executeQuery();
				while (res.next()) {

					Genes g1= idMap.get(res.getString("id1"));
					Genes g2= idMap.get(res.getString("id2"));
					if(g1!=null&&g2!=null) {
						archi.add(new Adiacenza(g1,g2,res.getDouble("peso")));
					}
				}
				res.close();
				st.close();
				conn.close();
				return ;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return ;
			}

	}

	
}
