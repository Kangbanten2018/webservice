<?php 

class Mahasiswa_model extends CI_Model
{
	public function getkkn($id = null)
	{
		if ($id === null) {
			return $this->db->get('kkn')->result_array();
		}else{
			return $this->db->get_where('kkn',['npm' => $id])->result_array();
		}
	}

	public function deletekkn($id)
	{
		$this->db->delete('kkn',['npm' => $id]);
		return $this->db->affected_rows();
	}

	public function createkkn($data)
	{
		$this->db->insert('kkn',$data);
		return $this->db->affected_rows();
	}

	public function updatekkn($data, $id)
	{
		$this->db->update('kkn',$data,['npm' => $id]);
		return $this->db->affected_rows();
	}
}