package com.example.homework_49.controller;

import com.example.homework_49.models.Candidate;
import com.example.homework_49.models.CandidateDataModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VoteServerController {
    private final CandidateDataModel candidateDataModel = new CandidateDataModel();

    @RequestMapping(value = "/")
    public String getCandidates(Model model) {
        model.addAttribute("candidades", candidateDataModel.getCandidates());
        return "candidades";
    }
    @RequestMapping(value = "/votes")
    public String getTotalVotes(Model model){
        List<Candidate> candidates = candidateDataModel.candidates;
        int totalVotes = 0;
        for (Candidate candidate : candidates) {
            totalVotes += candidate.getTotalVotes();
        }
        for (Candidate candidate : candidates) {
            if (candidate.getTotalVotes()!=0){
                double percentage = (double) candidate.getTotalVotes() / totalVotes * 100;
                candidate.setPercentageRatio(percentage);
            }

        }
        List<Candidate> sortedCandidates = candidates.stream()
                .sorted(Comparator.comparing(Candidate::getPercentageRatio).reversed())
                .collect(Collectors.toList());
        candidateDataModel.setCandidates(sortedCandidates);
        model.addAttribute("candidades", sortedCandidates);
        return "votes";
    }
    @PostMapping("/")
    public String voteForCandidate(@RequestParam("candidateId") int candidateId, HttpServletResponse response, Model model) {
        if (!hasUserAlreadyVoted()){
            for (Candidate candidate:candidateDataModel.candidates) {
                if (candidate.getCandidateID()==candidateId){
                    candidate.setTotalVotes(candidate.getTotalVotes()+1);
                    candidate.setAlreadyVoted(1);
                }
            }
        }else {
            for (Candidate candidate:candidateDataModel.candidates) {
                if (candidate.getCandidateID()==candidateId){
                    candidate.setTotalVotes(candidate.getTotalVotes()+1);
                }
            }
        }
        Cookie userCookie = new Cookie("candidateID",String.valueOf(candidateId));
        userCookie.setMaxAge(600);
        response.addCookie(userCookie);
        return "redirect:/thankyou";
    }

    private boolean hasUserAlreadyVoted() {
        for (Candidate candidate : candidateDataModel.candidates) {
            if (candidate.getAlreadyVoted() == 1) {
                return true;
            }
        }
        return false;
    }
  @RequestMapping(value = "/thankyou")
  private String getCandidateFromCookie(HttpServletRequest request,Model model){
      Cookie[] cookies = request.getCookies();
      int candidateId = -1;
      if (cookies != null) {
          for (Cookie cookie : cookies) {
              if (cookie.getName().equals("candidateID")) {
                  candidateId = Integer.parseInt(cookie.getValue());
              }
          }
      }
      List<Candidate> candidates = candidateDataModel.candidates;
      Candidate candidate = new Candidate("Anon","anon.jpg",0,0,0,0);;
      for (Candidate candidate1:candidates) {
          if (candidate1.getCandidateID()==candidateId){
              candidate=candidate1;
          }
      }
   model.addAttribute("candidate",candidate);

      return "thankyou";
  }

}
