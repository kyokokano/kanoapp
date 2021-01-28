package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Acce;

public class AcceDAO {
	private Connection db;
	private PreparedStatement ps;
	private ResultSet rs;

	private void connect() throws NamingException, SQLException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/acceapp");
		this.db = ds.getConnection();
	}

	private void disconnect()  {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (db != null) {
				db.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void ConnectCheck() {
		try {
			this.connect();
			System.out.println("OK");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
	public void insertOne(Acce acce) {
		try {
			this.connect();
			ps = db.prepareStatement("INSERT INTO accessories(name,price,imgname) VALUES(?,?,?)");
			ps.setString(1, acce.getName());
			ps.setInt(2, acce.getPrice());
			ps.setString(3, acce.getImgname());
			ps.execute();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
	}

	public List<Acce> findAll() {
		List<Acce> list = new ArrayList<>();
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM accessories");
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("name");
				int price = rs.getInt("price");
				String imgname = rs.getString("imgname");
				list.add(new Acce(id, title, price, imgname));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return list;
	}
	public Acce findOne(int id) {
		Acce acce = null;
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM accessories WHERE id=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				String name=rs.getString("name");
				int price=rs.getInt("price");
				String imgname=rs.getString("imgname");
				acce=new Acce(id,name,price,imgname);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return acce;
	}
	public void updateOne(Acce acce) {
		try {
			this.connect();
			ps=db.prepareStatement("UPDATE accessories SET name=?,price=?,imgname=? WHERE id =?");
			ps.setString(1,acce.getName());
			ps.setInt(2, acce.getPrice());
			ps.setString(3, acce.getImgname());
			ps.setInt(4, acce.getId());
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
	public void deleteOne(int id) {
		try {
			this.connect();
			ps=db.prepareStatement("DELETE FROM accessories WHERE id=?");
			ps.setInt(1, id);
			ps.execute();
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
	public List<Acce> getListBySearchWord(String searchWord){
		List<Acce> list=new ArrayList<>();
		searchWord="%"+searchWord+"%";
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM accessories WHERE name LIKE ?");
			ps.setString(1, searchWord);
			//System.out.println(ps);
			rs=ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("name");
				int price = rs.getInt("price");
				String imgname = rs.getString("imgname");
				list.add(new Acce(id, title, price, imgname));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return list;
	}
	public List<Acce> getListBySearchWord(String searchWord,int limit){
		List<Acce> list=new ArrayList<>();
		searchWord="%"+searchWord+"%";
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM accessories WHERE name LIKE ? LIMIT ?");
			ps.setString(1, searchWord);
			ps.setInt(2,limit);
			rs=ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("name");
				int price = rs.getInt("price");
				String imgname = rs.getString("imgname");
				list.add(new Acce(id, title, price, imgname));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return list;
	}
	public List<Acce> getListBySearchWord(String searchWord,int limit,int offset){
		List<Acce> list=new ArrayList<>();
		searchWord="%"+searchWord+"%";
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM accessories WHERE name LIKE ? LIMIT ? OFFSET ?");
			ps.setString(1, searchWord);
			ps.setInt(2,limit);
			ps.setInt(3,offset);
			rs=ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("name");
				int price = rs.getInt("price");
				String imgname = rs.getString("imgname");
				list.add(new Acce(id, title, price, imgname));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return list;
	}
	//一致件数を求めるメソッド
		public int getCount(String searchWord){
			searchWord="%"+searchWord+"%";
			int total=0;
			try {
				this.connect();
				ps = db.prepareStatement("SELECT count(*) AS total FROM accessories WHERE name LIKE ?");
				ps.setString(1, searchWord);
				rs = ps.executeQuery();
				if (rs.next()) {
					total = rs.getInt("total");
				}
			} catch (NamingException | SQLException e) {
				e.printStackTrace();
			} finally {
				this.disconnect();
			}
			return total;
		}
}