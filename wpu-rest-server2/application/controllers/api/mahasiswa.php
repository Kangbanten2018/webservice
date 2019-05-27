<?php

use Restserver\Libraries\REST_Controller;
defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . 'libraries/REST_Controller.php';
require APPPATH . 'libraries/Format.php';

class Mahasiswa extends REST_Controller
{
	public function __construct()
	{
		parent::__construct();
		$this->load->model('Mahasiswa_model','mahasiswa');

		$this->methods['index_get']['limit'] = 2;
	}

	public function index_get()
	{
		$id = $this->get('npm');
		if ($id === null) {
			$mahasiswa = $this->mahasiswa->getkkn();	
		} else{
			$mahasiswa = $this->mahasiswa->getkkn($id);
		}	
		
		if($mahasiswa){
			$this->response([
				'status' => true,
				'data' => $mahasiswa
			], REST_Controller::HTTP_OK);
		}else{
			$this->response([
				'status' => false,
				'message' => 'id not found'
			], REST_Controller::HTTP_NOT_FOUND);
		}
	}

	public function index_delete()
	{
		$id = $this->delete('npm');

		if ($id === null) {
			$this->response([
				'status' => false,
				'message' => 'provide an id'
			], REST_Controller::HTTP_BAD_REQUEST);
		}else{
			if ($this->mahasiswa->deletekkn($id) > 0) {
				//ok
				$this->response([
					'status' => true,
					'id' => $id,
					'message' => 'deleted'
			], REST_Controller::HTTP_NO_CONTENT);
			}else{
				//not found
				$this->response([
					'status' => false,
					'message' => 'id not found'
			], REST_Controller::HTTP_NOT_FOUND);
			}
		}
	}

	public function index_post()
	{
		$data = [
			'npm' => $this->post('npm'),
			'lokasi' => $this->post('lokasi'),
			'kelompok' => $this->post('kelompok')
			
		];

		if ($this->mahasiswa->createkkn($data) > 0) {
			$this->response([
					'status' => true,
					'message' => 'create success'
			], REST_Controller::HTTP_CREATED);
		}else{
			$this->response([
					'status' => false,
					'message' => 'failed to create new data'
			], REST_Controller::HTTP_NOT_FOUND);
		}
	}

	public function index_put()
	{
		$id = $this->put('npm');
		$data = [
			'lokasi' => $this->put('lokasi'),
			'kelompok' => $this->put('kelompok')
		];

		if ($this->mahasiswa->updatekkn($data,$id) > 0) {
			$this->response([
					'status' => true,
					'message' => 'modify success'
			], REST_Controller::HTTP_OK);
		}else{
			$this->response([
					'status' => false,
					'message' => 'failed to update new data'
			], REST_Controller::HTTP_BAD_REQUEST);
		}
	}
}