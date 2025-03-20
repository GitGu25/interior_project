package org.big.controller;

import java.util.List;

import org.big.dto.BoardDto;
import org.big.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@Autowired
	private BoardService BoardService;

	@GetMapping("/")
	public String home() {
		return "thymeleaf/index"; // templates/index.html 로 연결
	}

	@GetMapping("/review")
	public String review() {
		return "thymeleaf/review"; // templates/review.html
	}

	@GetMapping("/estimate")
	public String boardList(Model model) {
		try {
			List<BoardDto> list = BoardService.selectBoardList();
			model.addAttribute("list", list);
			System.out.println("pppppppppppppppppppppppppppppppp" + list.toString());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "목록을 불러오는 중 오류가 발생했습니다.");
		}

		return "thymeleaf/boardList";
	}

	@GetMapping("/cases")
	public String cases() {
		return "thymeleaf/cases"; // templates/cases.html
	}

}
