package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.ProjectCommentDto;
import com.itis.practice.team123.cvproject.dto.ProjectDto;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentProjectsRestController {

    private final ProjectsService projectsService;

    @PreAuthorize("hasAnyRole('TEACHER', 'COMPANY', 'ADMIN')")
    @GetMapping(value = {"/api/students/{id}/projects", "/load/students/{id}/projects"})
    public ResponseEntity<List<ProjectDto>> getStudentProjects(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(ProjectDto.from(projectsService.getAllProjects(id)));
    }

    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'COMPANY', 'ADMIN')")
    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok().body(ProjectDto.from(projectsService.getProjectById(projectId)));
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping(value = {"/api/studentAccount/projects", "/load/studentAccount/projects"})
    public ResponseEntity<List<ProjectDto>> getProjects(@AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        return ResponseEntity.ok().body(ProjectDto.from(projectsService.getAllProjects(userDetails.getUserId())));
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @PostMapping("/projects/newProject")
    public ResponseEntity<?> addNewProject(ProjectDto projectDto,
                                           @AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        projectsService.addNewProject(projectDto, userDetails.getUser());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping("/projects/{projectId}/newComment")
    public ResponseEntity<?> commentProject(@RequestBody ProjectCommentDto projectCommentDto,
                                            @PathVariable("projectId") Long projectId,
                                            @AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        projectsService.commentProject(projectCommentDto, projectId, userDetails.getUser() );
        return ResponseEntity.ok().build();
    }
}